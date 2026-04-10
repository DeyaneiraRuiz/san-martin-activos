package com.sgsi.riesgos.dto;

import jakarta.validation.constraints.NotNull;

public interface NivelRiesgoDto {
    record Request(
            @NotNull(message = "Nivel mínimo es obligatorio") Integer nivelMin,
            @NotNull(message = "Nivel máximo es obligatorio") Integer nivelMax,
            String clasificacion,
            String colorHex
    ) {}

    record Response(Integer id, Integer nivelMin, Integer nivelMax, String clasificacion, String colorHex) {}
}
