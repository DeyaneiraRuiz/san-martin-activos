package com.sgsi.procesos.dto;

import jakarta.validation.constraints.NotBlank;

public interface ControlDto {
    record Request(
            @NotBlank(message = "El código ISO es obligatorio") String codigoIso,
            @NotBlank(message = "El nombre es obligatorio") String nombre,
            String descripcion
    ) {}

    record Response(
            Integer id,
            String codigoIso,
            String nombre,
            String descripcion
    ) {}
}
