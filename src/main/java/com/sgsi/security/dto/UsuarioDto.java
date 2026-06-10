package com.sgsi.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

public interface UsuarioDto {

    record Request(
        @NotBlank(message = "El username es obligatorio")
        @Size(min = 3, max = 50) String username,

        String password,

        @NotBlank(message = "El email es obligatorio")
        @Email @Size(max = 100) String email,

        String estado,

        @NotEmpty(message = "Debe asignar al menos un rol")
        List<String> rolNombres,

        String ci,
        String complementoCi,
        String apellidoPaterno,
        String apellidoMaterno,
        String nombres,
        String celular,
        String cargo,
        String departamento
    ) {}

    record LoginRequest(
        @NotBlank(message = "El username es obligatorio") String username,
        @NotBlank(message = "La contraseña es obligatoria") String password
    ) {}

    record Response(
        Integer id,
        String username,
        String email,
        String estado,
        List<String> roles,
        String ci,
        String complementoCi,
        String apellidoPaterno,
        String apellidoMaterno,
        String nombres,
        String nombreCompleto,
        String celular,
        String cargo,
        String departamento,
        LocalDateTime ultimoLogin,
        LocalDateTime createdAt
    ) {}
}