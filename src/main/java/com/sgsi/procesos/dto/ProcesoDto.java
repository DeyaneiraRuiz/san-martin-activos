package com.sgsi.procesos.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public interface ProcesoDto {
    record Request(
            @NotBlank(message = "El nombre es obligatorio") String nombre,
            String tipo,
            String categoria,
            String descripcion,
            String impacto,
            String estado,
            Integer periodicidadId,
            Integer nivelAutomatizacionId,
            Boolean esCritico
    ) {}

    record Response(
            Integer id,
            String codigo,
            String nombre,
            String tipo,
            String categoria,
            String descripcion,
            String impacto,
            String estado,
            Integer periodicidadId,
            Integer nivelAutomatizacionId,
            Boolean esCritico,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {}
}
