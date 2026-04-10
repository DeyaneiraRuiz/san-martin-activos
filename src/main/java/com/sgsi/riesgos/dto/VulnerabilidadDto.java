package com.sgsi.riesgos.dto;

import jakarta.validation.constraints.NotBlank;

public interface VulnerabilidadDto {
    record Request(
            @NotBlank(message = "El nombre es obligatorio") String nombre,
            String descripcion,
            String tipo) {
    }

    record Response(Integer id, String nombre, String descripcion, String tipo) {
    }
}
