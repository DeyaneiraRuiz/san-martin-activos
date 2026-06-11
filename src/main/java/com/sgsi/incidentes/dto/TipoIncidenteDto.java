package com.sgsi.incidentes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public interface TipoIncidenteDto {

    record Request(

            @NotBlank(message = "El nombre es obligatorio")
            String nombre,

            @NotNull(message = "La categoría es obligatoria")
            Integer categoriaId,

            String estado

    ) {}

    record Response(

            Integer id,

            String codigo,

            String nombre,

            Integer categoriaId,

            String estado,

            String creadoPor,

            String modificadoPor,

            LocalDateTime createdAt,

            LocalDateTime updatedAt

    ) {}
}