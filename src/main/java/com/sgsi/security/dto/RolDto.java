package com.sgsi.security.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public interface RolDto {
        record Request(
                        @NotBlank(message = "El nombre del rol es obligatorio") @Size(max = 50) String nombre,

                        String descripcion) {
        }

        record Response(
                        Integer id,
                        String nombre,
                        String descripcion) {
        }
}
