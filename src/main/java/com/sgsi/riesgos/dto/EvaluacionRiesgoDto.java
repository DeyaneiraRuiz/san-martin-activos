package com.sgsi.riesgos.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public interface EvaluacionRiesgoDto {
    record Request(
            @NotNull(message = "El riesgo es obligatorio") Integer riesgoId,
            Integer probabilidad,
            Integer impacto,
            Integer nivelRiesgoId,
            LocalDateTime fecha,
            @NotNull(message = "El evaluador es obligatorio") Integer evaluadoPorId
    ) {}

    record Response(
            Integer id,
            Integer riesgoId,
            Integer probabilidad,
            Integer impacto,
            Integer nivelRiesgoId,
            LocalDateTime fecha,
            Integer evaluadoPorId
    ) {}
}
