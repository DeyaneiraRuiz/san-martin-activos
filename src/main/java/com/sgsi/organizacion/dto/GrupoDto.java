package com.sgsi.organizacion.dto;

import jakarta.validation.constraints.NotBlank;

public interface GrupoDto {
    record Request(
            @NotBlank(message = "El nombre es obligatorio") String nombre,
            String descripcion
    ) {}
    record Response(Integer id, String nombre, String descripcion) {}
}
