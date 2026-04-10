package com.sgsi.incidentes.dto;

import jakarta.validation.constraints.NotBlank;

public interface CategoriaIncidenteDto {
    record Request(
            @NotBlank(message = "El nombre es obligatorio") String nombre,
            String descripcion,
            Integer responsableId,
            Integer grupoId) {
    }

    record Response(Integer id, String nombre, String descripcion, Integer responsableId, Integer grupoId) {
    }
}
