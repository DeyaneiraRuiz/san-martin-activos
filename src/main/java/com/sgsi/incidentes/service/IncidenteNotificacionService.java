// com/sgsi/incidentes/service/IncidenteNotificacionService.java
package com.sgsi.incidentes.service;

import com.sgsi.incidentes.entity.Incidente;
import com.sgsi.notificaciones.dto.EmailDto;
import com.sgsi.notificaciones.service.EmailService;
import com.sgsi.security.entity.Usuario;
import com.sgsi.security.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class IncidenteNotificacionService {

    private final EmailService emailService;
    private final UsuarioRepository usuarioRepository;

    @Value("${app.frontend.url:http://localhost:4200}")
    private String frontendUrl;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    // ============================================
    // NOTIFICACIONES POR EVENTO
    // ============================================

    /**
     * Notificar creación de incidente
     * Destinatarios: Administradores + Observadores
     */
    public void notificarCreacion(Incidente incidente) {
        log.info("📧 Enviando notificación de creación para incidente: {}", incidente.getCodigo());

        Set<String> destinatarios = new HashSet<>();
        destinatarios.addAll(obtenerEmailsPorRol("ADMINISTRADOR"));
        destinatarios.addAll(obtenerEmailsPorRol("OBSERVADOR"));

        if (destinatarios.isEmpty()) {
            log.warn("No hay destinatarios para notificar creación del incidente {}", incidente.getCodigo());
            return;
        }

        String asunto = String.format("🔔 [NUEVO] Incidente #%s: %s", 
            incidente.getCodigo(), incidente.getTitulo());
        
        String mensajeHtml = construirMensajeHtml(incidente, "creado", null, null);
        
        enviarEmail(new ArrayList<>(destinatarios), asunto, mensajeHtml);
    }

    /**
     * Notificar asignación de técnico
     * Destinatarios: Técnico asignado + Reportador + Observadores
     */
    public void notificarAsignacion(Incidente incidente, Usuario tecnico) {
        log.info("📧 Enviando notificación de asignación para incidente: {} - Técnico: {}", 
            incidente.getCodigo(), tecnico.getEmail());

        Set<String> destinatarios = new HashSet<>();
        destinatarios.add(tecnico.getEmail());
        if (incidente.getReportador() != null) {
            destinatarios.add(incidente.getReportador().getEmail());
        }
        destinatarios.addAll(obtenerEmailsPorRol("OBSERVADOR"));

        if (destinatarios.isEmpty()) {
            log.warn("No hay destinatarios para notificar asignación del incidente {}", incidente.getCodigo());
            return;
        }

        String asunto = String.format("📌 [ASIGNADO] Incidente #%s: %s - Asignado a %s", 
            incidente.getCodigo(), incidente.getTitulo(), tecnico.getNombreCompleto());
        
        String mensajeHtml = construirMensajeHtml(incidente, "asignado", tecnico, null);
        
        enviarEmail(new ArrayList<>(destinatarios), asunto, mensajeHtml);
    }

    /**
     * Notificar planificación (estado cambia a EN_CURSO)
     * Destinatarios: Administradores + Reportador + Observadores
     */
    public void notificarPlanificacion(Incidente incidente) {
        log.info("📧 Enviando notificación de planificación para incidente: {}", incidente.getCodigo());

        Set<String> destinatarios = new HashSet<>();
        destinatarios.addAll(obtenerEmailsPorRol("ADMINISTRADOR"));
        if (incidente.getReportador() != null) {
            destinatarios.add(incidente.getReportador().getEmail());
        }
        destinatarios.addAll(obtenerEmailsPorRol("OBSERVADOR"));

        if (destinatarios.isEmpty()) {
            log.warn("No hay destinatarios para notificar planificación del incidente {}", incidente.getCodigo());
            return;
        }

        String asunto = String.format("⚙️ [EN_CURSO] Incidente #%s: %s - En proceso de atención", 
            incidente.getCodigo(), incidente.getTitulo());
        
        String mensajeHtml = construirMensajeHtml(incidente, "planificado", null, null);
        
        enviarEmail(new ArrayList<>(destinatarios), asunto, mensajeHtml);
    }

    /**
     * Notificar resolución (estado cambia a RESUELTO)
     * Destinatarios: Administradores + Reportador + Observadores
     */
    public void notificarResolucion(Incidente incidente) {
        log.info("📧 Enviando notificación de resolución para incidente: {}", incidente.getCodigo());

        Set<String> destinatarios = new HashSet<>();
        destinatarios.addAll(obtenerEmailsPorRol("ADMINISTRADOR"));
        if (incidente.getReportador() != null) {
            destinatarios.add(incidente.getReportador().getEmail());
        }
        destinatarios.addAll(obtenerEmailsPorRol("OBSERVADOR"));

        if (destinatarios.isEmpty()) {
            log.warn("No hay destinatarios para notificar resolución del incidente {}", incidente.getCodigo());
            return;
        }

        String asunto = String.format("✅ [RESUELTO] Incidente #%s: %s - Solución aplicada", 
            incidente.getCodigo(), incidente.getTitulo());
        
        String mensajeHtml = construirMensajeHtml(incidente, "resuelto", null, null);
        
        enviarEmail(new ArrayList<>(destinatarios), asunto, mensajeHtml);
    }

    /**
     * Notificar cierre (estado cambia a CERRADO)
     * Destinatarios: Técnico asignado + Reportador + Observadores
     */
    public void notificarCierre(Incidente incidente) {
        log.info("📧 Enviando notificación de cierre para incidente: {}", incidente.getCodigo());

        Set<String> destinatarios = new HashSet<>();
        if (incidente.getAsignadoA() != null) {
            destinatarios.add(incidente.getAsignadoA().getEmail());
        }
        if (incidente.getReportador() != null) {
            destinatarios.add(incidente.getReportador().getEmail());
        }
        destinatarios.addAll(obtenerEmailsPorRol("OBSERVADOR"));

        if (destinatarios.isEmpty()) {
            log.warn("No hay destinatarios para notificar cierre del incidente {}", incidente.getCodigo());
            return;
        }

        String asunto = String.format("🔒 [CERRADO] Incidente #%s: %s - Cerrado definitivamente", 
            incidente.getCodigo(), incidente.getTitulo());
        
        String mensajeHtml = construirMensajeHtml(incidente, "cerrado", null, null);
        
        enviarEmail(new ArrayList<>(destinatarios), asunto, mensajeHtml);
    }

    /**
     * Notificar rechazo de solución
     * Destinatarios: Técnico asignado + Reportador + Observadores
     */
    public void notificarRechazo(Incidente incidente, String motivo) {
        log.info("📧 Enviando notificación de rechazo para incidente: {}", incidente.getCodigo());

        Set<String> destinatarios = new HashSet<>();
        if (incidente.getAsignadoA() != null) {
            destinatarios.add(incidente.getAsignadoA().getEmail());
        }
        if (incidente.getReportador() != null) {
            destinatarios.add(incidente.getReportador().getEmail());
        }
        destinatarios.addAll(obtenerEmailsPorRol("OBSERVADOR"));

        if (destinatarios.isEmpty()) {
            log.warn("No hay destinatarios para notificar rechazo del incidente {}", incidente.getCodigo());
            return;
        }

        String asunto = String.format("⚠️ [RECHAZADO] Incidente #%s: %s - Solución rechazada", 
            incidente.getCodigo(), incidente.getTitulo());
        
        String mensajeHtml = construirMensajeHtml(incidente, "rechazado", null, motivo);
        
        enviarEmail(new ArrayList<>(destinatarios), asunto, mensajeHtml);
    }

    // ============================================
    // MÉTODOS AUXILIARES
    // ============================================

    /**
     * Obtener emails de usuarios por nombre de rol
     */
    private Set<String> obtenerEmailsPorRol(String nombreRol) {
        return usuarioRepository.findAll().stream()
            .filter(usuario -> usuario.getActivo() != null && usuario.getActivo())
            .filter(usuario -> {
                if (usuario.getRoles() == null) return false;
                return usuario.getRoles().stream()
                    .anyMatch(rol -> nombreRol.equalsIgnoreCase(rol.getNombre()));
            })
            .map(Usuario::getEmail)
            .filter(email -> email != null && !email.isBlank())
            .collect(Collectors.toSet());
    }

    /**
     * Construir mensaje HTML para el email
     */
    private String construirMensajeHtml(Incidente incidente, String evento, Usuario tecnico, String motivoRechazo) {
        String urlIncidente = String.format("%s/incidentes/editar/%d", frontendUrl, incidente.getId());
        
        String fechaFormateada = incidente.getCreatedAt() != null 
            ? incidente.getCreatedAt().format(DATE_FORMATTER) 
            : "Fecha no disponible";

        // Icono según evento
        String icono = switch (evento) {
            case "creado" -> "🆕";
            case "asignado" -> "👤";
            case "planificado" -> "⚙️";
            case "resuelto" -> "✅";
            case "cerrado" -> "🔒";
            case "rechazado" -> "⚠️";
            default -> "📋";
        };

        String tituloEvento = switch (evento) {
            case "creado" -> "ha sido creado";
            case "asignado" -> "ha sido asignado";
            case "planificado" -> "está en proceso de atención";
            case "resuelto" -> "ha sido resuelto";
            case "cerrado" -> "ha sido cerrado";
            case "rechazado" -> "ha sido rechazado";
            default -> "ha sido actualizado";
        };

        // Obtener nombres de estados y gravedad
        String estadoNombre = incidente.getEstado() != null ? incidente.getEstado().getNombre() : "No definido";
        String gravedadNombre = incidente.getGravedadReportada() != null ? incidente.getGravedadReportada().getNombre() : "No definida";

        StringBuilder mensaje = new StringBuilder();
        mensaje.append(String.format("""
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <style>
                    body {
                        font-family: 'Segoe UI', Arial, sans-serif;
                        max-width: 650px;
                        margin: 0 auto;
                        padding: 0;
                        color: #333;
                    }
                    .header {
                        background: linear-gradient(135deg, #eab308, #ca8a04);
                        padding: 20px;
                        text-align: center;
                        border-radius: 12px 12px 0 0;
                    }
                    .header h1 {
                        margin: 0;
                        color: white;
                        font-size: 24px;
                    }
                    .content {
                        background-color: #ffffff;
                        padding: 25px;
                        border: 1px solid #e5e7eb;
                        border-top: none;
                        border-radius: 0 0 12px 12px;
                    }
                    .incidente-card {
                        background-color: #f9fafb;
                        border-radius: 8px;
                        padding: 15px;
                        margin: 15px 0;
                        border-left: 4px solid #eab308;
                    }
                    .info-row {
                        display: flex;
                        margin-bottom: 8px;
                        flex-wrap: wrap;
                    }
                    .info-label {
                        font-weight: 600;
                        width: 120px;
                        color: #4b5563;
                    }
                    .info-value {
                        color: #1f2937;
                        flex: 1;
                    }
                    .button {
                        background-color: #eab308;
                        color: white;
                        padding: 12px 24px;
                        text-decoration: none;
                        border-radius: 6px;
                        display: inline-block;
                        margin-top: 20px;
                        font-weight: 600;
                    }
                    .footer {
                        font-size: 12px;
                        color: #9ca3af;
                        text-align: center;
                        margin-top: 25px;
                        padding-top: 15px;
                        border-top: 1px solid #e5e7eb;
                    }
                    .badge {
                        display: inline-block;
                        padding: 4px 8px;
                        border-radius: 12px;
                        font-size: 12px;
                        font-weight: 600;
                    }
                    .motivo-rechazo {
                        background-color: #fef2f2;
                        border-left: 4px solid #dc2626;
                        padding: 10px;
                        margin-top: 15px;
                        border-radius: 6px;
                    }
                </style>
            </head>
            <body>
                <div class="header">
                    <h1>%s SGSI - Sistema de Gestión de Incidentes</h1>
                </div>
                <div class="content">
                    <p>Estimado/a usuario,</p>
                    <p>Se le informa que el incidente <strong>#%s</strong> <strong>%s</strong>.</p>
                    
                    <div class="incidente-card">
                        <div class="info-row">
                            <span class="info-label">📌 Código:</span>
                            <span class="info-value"><strong>%s</strong></span>
                        </div>
                        <div class="info-row">
                            <span class="info-label">📝 Título:</span>
                            <span class="info-value">%s</span>
                        </div>
                        <div class="info-row">
                            <span class="info-label">🏷️ Estado:</span>
                            <span class="info-value"><strong>%s</strong></span>
                        </div>
                        <div class="info-row">
                            <span class="info-label">⚠️ Gravedad:</span>
                            <span class="info-value"><strong>%s</strong></span>
                        </div>
                        <div class="info-row">
                            <span class="info-label">👤 Reportador:</span>
                            <span class="info-value">%s</span>
                        </div>
            """, 
            icono, incidente.getCodigo(), tituloEvento,
            incidente.getCodigo(),
            incidente.getTitulo(),
            estadoNombre,
            gravedadNombre,
            incidente.getReportador() != null ? incidente.getReportador().getNombreCompleto() : "No especificado"));

        // Información del técnico si está asignado
        if (tecnico != null) {
            mensaje.append("""
                        <div class="info-row">
                            <span class="info-label">🔧 Técnico asignado:</span>
                            <span class="info-value">%s</span>
                        </div>
            """.formatted(tecnico.getNombreCompleto()));
        } else if (incidente.getAsignadoA() != null && !"asignado".equals(evento)) {
            mensaje.append("""
                        <div class="info-row">
                            <span class="info-label">🔧 Técnico asignado:</span>
                            <span class="info-value">%s</span>
                        </div>
            """.formatted(incidente.getAsignadoA().getNombreCompleto()));
        }

        // Fecha de planificación si existe
        if (incidente.getFechaCompromiso() != null) {
            mensaje.append("""
                        <div class="info-row">
                            <span class="info-label">📅 Fecha compromiso:</span>
                            <span class="info-value">%s</span>
                        </div>
            """.formatted(incidente.getFechaCompromiso().format(DATE_FORMATTER)));
        }

        // Descripción
        if (incidente.getDescripcion() != null && !incidente.getDescripcion().isEmpty()) {
            mensaje.append("""
                        <div class="info-row">
                            <span class="info-label">📄 Descripción:</span>
                            <span class="info-value">%s</span>
                        </div>
            """.formatted(incidente.getDescripcion()));
        }

        // Causa raíz y solución si están disponibles
        if (incidente.getCausaRaiz() != null && !incidente.getCausaRaiz().isEmpty()) {
            mensaje.append("""
                        <div class="info-row">
                            <span class="info-label">🔍 Causa raíz:</span>
                            <span class="info-value">%s</span>
                        </div>
            """.formatted(incidente.getCausaRaiz()));
        }

        if (incidente.getSolucionPropuesta() != null && !incidente.getSolucionPropuesta().isEmpty()) {
            mensaje.append("""
                        <div class="info-row">
                            <span class="info-label">💡 Solución propuesta:</span>
                            <span class="info-value">%s</span>
                        </div>
            """.formatted(incidente.getSolucionPropuesta()));
        }

        if (incidente.getDescripcionSolucion() != null && !incidente.getDescripcionSolucion().isEmpty()) {
            mensaje.append("""
                        <div class="info-row">
                            <span class="info-label">✅ Solución aplicada:</span>
                            <span class="info-value">%s</span>
                        </div>
            """.formatted(incidente.getDescripcionSolucion()));
        }

        if (incidente.getComentarioCierre() != null && !incidente.getComentarioCierre().isEmpty()) {
            mensaje.append("""
                        <div class="info-row">
                            <span class="info-label">💬 Comentario cierre:</span>
                            <span class="info-value">%s</span>
                        </div>
            """.formatted(incidente.getComentarioCierre()));
        }

        // Motivo de rechazo si existe
        if (motivoRechazo != null && !motivoRechazo.isEmpty()) {
            mensaje.append("""
                        <div class="motivo-rechazo">
                            <strong>❌ Motivo del rechazo:</strong><br>
                            %s
                        </div>
            """.formatted(motivoRechazo));
        }

        mensaje.append("""
                    </div>
                    
                    <div style="text-align: center;">
                        <a href="%s" class="button">🔗 Ver incidente</a>
                    </div>
                    
                    <div class="footer">
                        <p>Este es un mensaje automático del Sistema de Gestión de Incidentes.<br>
                        Por favor, no responda a este correo.</p>
                        <p>📅 %s</p>
                    </div>
                </div>
            </body>
            </html>
            """.formatted(urlIncidente, fechaFormateada));

        return mensaje.toString();
    }

    /**
     * Enviar email
     */
    private void enviarEmail(List<String> destinatarios, String asunto, String mensajeHtml) {
        if (destinatarios == null || destinatarios.isEmpty()) {
            log.warn("No hay destinatarios para enviar email");
            return;
        }

        EmailDto email = EmailDto.builder()
                .destinatarios(destinatarios)
                .asunto(asunto)
                .mensajeHtml(mensajeHtml)
                .build();

        emailService.enviarEmail(email);
        log.info("✅ Notificación enviada a {} destinatarios", destinatarios.size());
    }
}