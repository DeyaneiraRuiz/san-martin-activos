package com.sgsi.incidentes.service;

import com.sgsi.activos.entity.Activo;
import com.sgsi.activos.repository.ActivoRepository;
import com.sgsi.catalogos.entity.CatEstadoIncidente;
import com.sgsi.catalogos.entity.CatNivel;
import com.sgsi.catalogos.repository.CatEstadoIncidenteRepository;
import com.sgsi.catalogos.repository.CatNivelRepository;
import com.sgsi.incidentes.dto.IncidenteDto;
import com.sgsi.incidentes.entity.CategoriaIncidente;
import com.sgsi.incidentes.entity.Incidente;
import com.sgsi.incidentes.entity.TipoIncidente;
import com.sgsi.incidentes.repository.CategoriaIncidenteRepository;
import com.sgsi.incidentes.repository.IncidenteRepository;
import com.sgsi.incidentes.repository.TipoIncidenteRepository;
import com.sgsi.security.entity.Usuario;
import com.sgsi.security.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class IncidenteService {

    private final IncidenteRepository incidenteRepository;

    private final CategoriaIncidenteRepository categoriaRepository;
    private final TipoIncidenteRepository tipoIncidenteRepository;

    private final CatNivelRepository nivelRepository;
    private final CatEstadoIncidenteRepository estadoRepository;

    private final UsuarioRepository usuarioRepository;
    private final ActivoRepository activoRepository;

    private final IncidenteNotificacionService notificacionService;

    public List<Incidente> findAll() {
        return incidenteRepository.findAll();
    }

    public Optional<Incidente> findById(Integer id) {
        return incidenteRepository.findById(id);
    }

    public Incidente crear(Incidente incidente) {

        if (incidente.getCodigo() == null || incidente.getCodigo().isBlank()) {
            incidente.setCodigo(generarCodigo());
        }

        if (incidente.getFechaReporte() == null) {
            incidente.setFechaReporte(LocalDateTime.now());
        }

        incidente.setUltimaModificacion(LocalDateTime.now());

        Incidente guardado = incidenteRepository.save(incidente);

        // 📧 Notificar creación
        notificacionService.notificarCreacion(guardado);

        return guardado;
    }

    public Incidente actualizar(Incidente incidente) {

        incidente.setUltimaModificacion(LocalDateTime.now());

        return incidenteRepository.save(incidente);
    }

    /**
     * Crear desde DTO
     */
    public Incidente crear(IncidenteDto.Request request) {

        Incidente incidente = new Incidente();

        mapearRelaciones(request, incidente);

        incidente.setCodigo(request.codigo());
        incidente.setTitulo(request.titulo());
        incidente.setDescripcion(request.descripcion());

        incidente.setFechaDeteccion(request.fechaDeteccion());

        incidente.setFechaAsignacion(request.fechaAsignacion());
        incidente.setFechaCompromiso(request.fechaCompromiso());

        incidente.setCausaRaiz(request.causaRaiz());
        incidente.setSolucionPropuesta(request.solucionPropuesta());
        incidente.setPlanAccion(request.planAccion());

        incidente.setDescripcionSolucion(request.descripcionSolucion());
        incidente.setFechaResolucion(request.fechaResolucion());

        incidente.setComentarioCierre(request.comentarioCierre());
        incidente.setFechaCierre(request.fechaCierre());

        incidente.setReabierto(request.reabierto());
        incidente.setMotivoReapertura(request.motivoReapertura());
        incidente.setFechaReapertura(request.fechaReapertura());

        return crear(incidente);
    }

    /**
     * Actualizar desde DTO
     */
    public Incidente actualizar(Integer id, IncidenteDto.Request request) {

        Incidente incidente = incidenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incidente no encontrado"));

        // Guardar estado anterior para comparar cambios importantes
        String estadoAnterior = null;
        Integer tecnicoAnteriorId = null;

        if (incidente.getAsignadoA() != null) {
            tecnicoAnteriorId = incidente.getAsignadoA().getId();
        }

        mapearRelaciones(request, incidente);

        incidente.setTitulo(request.titulo());
        incidente.setDescripcion(request.descripcion());

        incidente.setFechaDeteccion(request.fechaDeteccion());

        incidente.setFechaAsignacion(request.fechaAsignacion());
        incidente.setFechaCompromiso(request.fechaCompromiso());

        incidente.setCausaRaiz(request.causaRaiz());
        incidente.setSolucionPropuesta(request.solucionPropuesta());
        incidente.setPlanAccion(request.planAccion());

        incidente.setDescripcionSolucion(request.descripcionSolucion());
        incidente.setFechaResolucion(request.fechaResolucion());

        incidente.setComentarioCierre(request.comentarioCierre());
        incidente.setFechaCierre(request.fechaCierre());

        incidente.setReabierto(request.reabierto());
        incidente.setMotivoReapertura(request.motivoReapertura());
        incidente.setFechaReapertura(request.fechaReapertura());

        Incidente actualizado = actualizar(incidente);

        // 📧 Notificar según los cambios detectados
        evaluarYNotificarCambios(actualizado, estadoAnterior, tecnicoAnteriorId);

        return actualizado;
    }

    /**
     * Método específico para asignar técnico
     */
    public Incidente asignarTecnico(Integer id, Integer tecnicoId) {
        Incidente incidente = incidenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incidente no encontrado"));

        Usuario tecnico = buscarUsuario(tecnicoId);

        incidente.setAsignadoA(tecnico);
        incidente.setFechaAsignacion(LocalDateTime.now());

        // Cambiar estado si estaba en nuevo
        if (incidente.getEstado() != null && "nuevo".equals(incidente.getEstado().getNombre().toLowerCase())) {
            CatEstadoIncidente estadoAsignado = estadoRepository.findById(2)
                    .orElseThrow(() -> new RuntimeException("Estado 'Asignado' no encontrado"));
            incidente.setEstado(estadoAsignado);
        }

        incidente.setUltimaModificacion(LocalDateTime.now());

        Incidente actualizado = incidenteRepository.save(incidente);

        // 📧 Notificar asignación
        notificacionService.notificarAsignacion(actualizado, tecnico);

        return actualizado;
    }

    /**
     * Cambiar estado de un incidente
     */
    public Incidente cambiarEstado(Integer id, Integer nuevoEstadoId) {
        Incidente incidente = incidenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incidente no encontrado"));

        CatEstadoIncidente nuevoEstado = buscarEstado(nuevoEstadoId);
        String estadoActualNombre = incidente.getEstado() != null ? incidente.getEstado().getNombre().toLowerCase()
                : null;
        String nuevoEstadoNombre = nuevoEstado.getNombre().toLowerCase();

        incidente.setEstado(nuevoEstado);
        incidente.setUltimaModificacion(LocalDateTime.now());

        // Actualizar fechas específicas según el estado
        if ("en_curso".equals(nuevoEstadoNombre)) {
            // No hay fecha específica, solo notificar
        } else if ("resuelto".equals(nuevoEstadoNombre)) {
            incidente.setFechaResolucion(LocalDateTime.now());
        } else if ("cerrado".equals(nuevoEstadoNombre)) {
            incidente.setFechaCierre(LocalDateTime.now());
        }

        Incidente actualizado = incidenteRepository.save(incidente);

        // 📧 Notificar según el nuevo estado
        switch (nuevoEstadoNombre) {
            case "en_curso":
                notificacionService.notificarPlanificacion(actualizado);
                break;
            case "resuelto":
                notificacionService.notificarResolucion(actualizado);
                break;
            case "cerrado":
                notificacionService.notificarCierre(actualizado);
                break;
            case "asignado":
                if (incidente.getAsignadoA() != null) {
                    notificacionService.notificarAsignacion(actualizado, incidente.getAsignadoA());
                }
                break;
            default:
                log.info("Estado cambiado a: {}", nuevoEstadoNombre);
        }

        return actualizado;
    }

    /**
     * Rechazar solución (vuelve a estado asignado)
     */
    public Incidente rechazarSolucion(Integer id, String motivoRechazo) {
        Incidente incidente = incidenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Incidente no encontrado"));

        CatEstadoIncidente estadoAsignado = estadoRepository.findById(2)
                .orElseThrow(() -> new RuntimeException("Estado 'Asignado' no encontrado"));

        incidente.setEstado(estadoAsignado);
        incidente.setUltimaModificacion(LocalDateTime.now());

        Incidente actualizado = incidenteRepository.save(incidente);

        // 📧 Notificar rechazo
        notificacionService.notificarRechazo(actualizado, motivoRechazo);

        return actualizado;
    }

    public void deleteById(Integer id) {
        incidenteRepository.deleteById(id);
    }

    /**
     * Evaluar qué cambió y notificar apropiadamente
     */
    private void evaluarYNotificarCambios(Incidente incidente, String estadoAnterior, Integer tecnicoAnteriorId) {
        String estadoActual = incidente.getEstado() != null ? incidente.getEstado().getNombre().toLowerCase() : null;
        Integer tecnicoActualId = incidente.getAsignadoA() != null ? incidente.getAsignadoA().getId() : null;

        // Verificar si hubo cambio de técnico
        if (tecnicoActualId != null && !tecnicoActualId.equals(tecnicoAnteriorId)) {
            notificacionService.notificarAsignacion(incidente, incidente.getAsignadoA());
        }

        // Verificar cambio a resolucion
        if ("resuelto".equals(estadoActual)) {
            notificacionService.notificarResolucion(incidente);
        }

        // Verificar cambio a cierre
        if ("cerrado".equals(estadoActual)) {
            notificacionService.notificarCierre(incidente);
        }
    }

    private void mapearRelaciones(
            IncidenteDto.Request request,
            Incidente incidente) {

        incidente.setCategoria(
                buscarCategoria(request.categoriaId()));

        incidente.setTipoIncidente(
                buscarTipoIncidente(request.tipoIncidenteId()));

        incidente.setGravedadReportada(
                buscarNivel(request.gravedadReportadaId()));

        incidente.setImpactoReal(
                buscarNivel(request.impactoRealId()));

        incidente.setPrioridad(
                buscarNivel(request.prioridadId()));

        incidente.setEstado(
                buscarEstado(request.estadoId()));

        incidente.setReportador(
                buscarUsuario(request.reportadorId()));

        incidente.setAsignadoA(
                buscarUsuario(request.asignadoAId()));

        incidente.setAsignadoPor(
                buscarUsuario(request.asignadoPorId()));

        incidente.setCerradoPor(
                buscarUsuario(request.cerradoPorId()));

        incidente.setReabiertoPor(
                buscarUsuario(request.reabiertoPorId()));

        incidente.setObservadores(
                buscarObservadores(request.observadorIds()));

        incidente.setActivosAfectados(
                buscarActivos(request.activoIds()));
    }

    private CategoriaIncidente buscarCategoria(Integer id) {

        if (id == null)
            return null;

        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    }

    private TipoIncidente buscarTipoIncidente(Integer id) {

        if (id == null)
            return null;

        return tipoIncidenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de incidente no encontrado"));
    }

    private CatNivel buscarNivel(Integer id) {

        if (id == null)
            return null;

        return nivelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nivel no encontrado"));
    }

    private CatEstadoIncidente buscarEstado(Integer id) {

        if (id == null)
            return null;

        return estadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado"));
    }

    private Usuario buscarUsuario(Integer id) {

        if (id == null)
            return null;

        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    private Set<Usuario> buscarObservadores(Set<Integer> ids) {

        if (ids == null || ids.isEmpty()) {
            return new HashSet<>();
        }

        return new HashSet<>(
                usuarioRepository.findAllById(ids));
    }

    private Set<Activo> buscarActivos(Set<Integer> ids) {

        if (ids == null || ids.isEmpty()) {
            return new HashSet<>();
        }

        return new HashSet<>(
                activoRepository.findAllById(ids));
    }

    private String generarCodigo() {

        long total = incidenteRepository.count() + 1;

        String codigo;

        do {
            codigo = String.format("INC-%06d", total++);
        } while (incidenteRepository.existsByCodigo(codigo));

        return codigo;
    }
}