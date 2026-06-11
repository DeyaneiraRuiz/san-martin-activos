package com.sgsi.incidentes.dto;

import jakarta.validation.constraints.NotBlank;

public interface CategoriaIncidenteDto {
    record Request(
            @NotBlank(message = "El nombre es obligatorio") String nombre,
            String descripcion,
            String estado,
            Integer responsableId,
            Integer grupoId) {
    }

    record Response(
            Integer id,
            String codigo,
            String nombre,
            String descripcion,
            String estado,
            Integer responsableId,
            Integer grupoId,
            java.time.LocalDateTime createdAt) {
    }
}
