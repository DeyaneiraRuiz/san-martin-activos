package com.sgsi.riesgos.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public interface TratamientoRiesgoDto {
    record Request(
            @NotNull(message = "El riesgo es obligatorio") Integer riesgoId,
            String estrategia,
            Integer responsableId,
            LocalDate fechaPlazo,
            String estado
    ) {}

    record Response(
            Integer id,
            Integer riesgoId,
            String estrategia,
            Integer responsableId,
            LocalDate fechaPlazo,
            String estado
    ) {}
}
