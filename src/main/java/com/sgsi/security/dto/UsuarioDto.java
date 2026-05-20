package com.sgsi.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public interface UsuarioDto {
        record Request(
                        @NotBlank(message = "El username es obligatorio") @Size(min = 3, max = 50, message = "El nombre de usuario debe tener entre 3 y 50 caracteres") String username,

                        @NotBlank(message = "La contraseña es obligatoria") @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres") String password,

                        @NotBlank(message = "El email es obligatorio") @Email(message = "Debe ser un email válido") @Size(max = 100) String email,

                        Boolean activo,

                        @NotNull(message = "El rol es obligatorio") Integer rolId) {
        }

        record LoginRequest(
                        @NotBlank(message = "El username es obligatorio") String username,
                        @NotBlank(message = "La contraseña es obligatoria") String password) {
        }

        record Response(
                        Integer id,
                        String username,
                        String email,
                        Boolean activo,
                        Integer rolId) {
        }
}