package com.sgsi.incidentes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface SlaDto {
    record Request(
            @NotBlank(message = "El nivel es obligatorio") String nivel,
            @NotNull(message = "El tiempo de respuesta es obligatorio") Integer tiempoRespuestaHoras,
            @NotNull(message = "El tiempo de resolución es obligatorio") Integer tiempoResolucionHoras,
            String descripcion
    ) {}
    record Response(Integer id, String nivel, Integer tiempoRespuestaHoras, Integer tiempoResolucionHoras, String descripcion) {}
}
