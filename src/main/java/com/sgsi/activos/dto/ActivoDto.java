package com.sgsi.activos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ActivoDto {
    record Request(
            @NotBlank(message = "El nombre es obligatorio") String nombre,
            String descripcion,
            Integer propietarioId,
            @NotNull(message = "El ID de organización (área) es obligatorio") Integer areaId,
            @NotNull(message = "El tipo de activo es obligatorio") Integer tipoActivoId,
            @NotNull(message = "El estado del activo es obligatorio") Integer estadoId,
            Integer confidencialidad,
            Integer integridad,
            Integer disponibilidad,
            String ubicacion,
            LocalDate fechaAdquisicion
    ) {}

    record Response(
            Integer id,
            String codigo,
            String nombre,
            String descripcion,
            Integer propietarioId,
            Integer areaId,
            Integer tipoActivoId,
            Integer estadoId,
            Integer confidencialidad,
            Integer integridad,
            Integer disponibilidad,
            String ubicacion,
            LocalDate fechaAdquisicion,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {}
}
