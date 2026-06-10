package com.sgsi.organizacion.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public interface GrupoDto {
    record Request(
            @NotBlank(message = "El nombre es obligatorio") String nombre,
            String descripcion
    ) {}
    record Response(Integer id, String codigo, String nombre, String descripcion, LocalDateTime createdAt) {}
}
