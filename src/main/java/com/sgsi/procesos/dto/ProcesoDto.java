package com.sgsi.procesos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public interface ProcesoDto {
    record Request(
            @NotBlank(message = "El nombre es obligatorio") String nombre,
            @NotNull Integer periodicidadId,
            @NotNull Integer nivelAutomatizacionId,
            Boolean esCritico
    ) {}

    record Response(
            Integer id,
            String nombre,
            Integer periodicidadId,
            Integer nivelAutomatizacionId,
            Boolean esCritico,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {}
}
