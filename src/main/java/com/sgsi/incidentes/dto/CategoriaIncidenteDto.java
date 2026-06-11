package com.sgsi.incidentes.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public interface CategoriaIncidenteDto {
    record Request(
            @NotBlank(message = "El nombre es obligatorio")
            String nombre,
            String descripcion,
            String codigo,
            String estado,
            Integer responsableId

    ) {}

    record Response(
            Integer id,
            String codigo,
            String nombre,
            String descripcion,
            String estado,
            Integer responsableId,
            LocalDateTime createdAt
    ) {}
}
