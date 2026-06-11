package com.sgsi.incidentes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface SlaDto {

    record Request(

            @NotBlank(message = "El nombre es obligatorio")
            String nombre,

            @NotNull(message = "El tiempo de respuesta es obligatorio")
            Integer tiempoRespuesta,

            @NotNull(message = "El tiempo de resolución es obligatorio")
            Integer tiempoResolucion

    ) {}

    record Response(

            Integer id,

            String nombre,

            Integer tiempoRespuesta,

            Integer tiempoResolucion

    ) {}
}