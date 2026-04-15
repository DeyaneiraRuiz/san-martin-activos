package com.sgsi.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public interface UsuarioDto {
        record Request(
                        @NotBlank(message = "El username es obligatorio") @Size(max = 50) String username,

                        @NotBlank(message = "La contraseña es obligatoria") String password,

                        @NotBlank(message = "El email es obligatorio") @Email(message = "Debe ser un email válido") @Size(max = 100) String email,

                        Boolean activo,

                        @NotNull(message = "El rol es obligatorio") Integer rolId) {
        }

        record Response(
                        Integer id,
                        String username,
                        String email,
                        Boolean activo,
                        Integer rolId) {
        }
}