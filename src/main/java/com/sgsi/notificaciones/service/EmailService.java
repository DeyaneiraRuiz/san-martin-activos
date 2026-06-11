// com/sgsi/notificaciones/service/EmailService.java
package com.sgsi.notificaciones.service;

import com.sgsi.notificaciones.dto.EmailDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    /**
     * Envía un email a los destinatarios especificados
     * @param email DTO con la información del email
     */
    public void enviarEmail(EmailDto email) {
        if (email.getDestinatarios() == null || email.getDestinatarios().isEmpty()) {
            log.warn("No hay destinatarios para enviar el email");
            return;
        }

        try {
            var mimeMessage = mailSender.createMimeMessage();
            var helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setTo(email.getDestinatarios().toArray(new String[0]));
            helper.setSubject(email.getAsunto());
            
            // Usar HTML si está disponible, si no usar texto plano
            if (email.getMensajeHtml() != null && !email.getMensajeHtml().isEmpty()) {
                helper.setText(email.getMensajeHtml(), true);
            } else if (email.getMensajeTexto() != null) {
                helper.setText(email.getMensajeTexto(), false);
            } else {
                log.warn("No hay contenido para el email");
                return;
            }

            mailSender.send(mimeMessage);
            log.info("✅ Email enviado a: {}", email.getDestinatarios());

        } catch (Exception e) {
            log.error("❌ Error al enviar email: {}", e.getMessage(), e);
        }
    }

    /**
     * Envía un email en formato HTML
     */
    public void enviarEmailHtml(List<String> destinatarios, String asunto, String htmlContent) {
        EmailDto email = EmailDto.builder()
                .destinatarios(destinatarios)
                .asunto(asunto)
                .mensajeHtml(htmlContent)
                .build();
        enviarEmail(email);
    }

    /**
     * Envía un email en texto plano
     */
    public void enviarEmailTexto(List<String> destinatarios, String asunto, String textoContent) {
        EmailDto email = EmailDto.builder()
                .destinatarios(destinatarios)
                .asunto(asunto)
                .mensajeTexto(textoContent)
                .build();
        enviarEmail(email);
    }
}