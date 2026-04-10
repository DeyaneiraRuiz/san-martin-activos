package com.sgsi.procesos.dto;

import jakarta.validation.constraints.NotNull;

public interface ActivoProcesoDto {
    record Request(
            @NotNull(message = "El activo es obligatorio") Integer activoId,
            @NotNull(message = "El proceso es obligatorio") Integer procesoId,
            String justificacion
    ) {}
    record Response(Integer id, Integer activoId, Integer procesoId, String justificacion) {}
}
