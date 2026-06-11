package com.sgsi.notificaciones.dto;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class EmailDto {
    
    private List<String> destinatarios;
    
    private String asunto;
    
    private String mensajeHtml;
    
    private String mensajeTexto;
}