package com.sgsi.organizacion.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface PropietarioDto {
    record Request(
            @NotBlank(message = "El nombre es obligatorio") String nombre,
            @NotBlank(message = "El apellido es obligatorio") String apellido,
            String cargo,
            @Email(message = "Email inválido") String email,
            String telefono,
            @NotNull(message = "El ID de área es obligatorio") Integer areaId
    ) {}
    record Response(Integer id, String nombre, String apellido, String cargo, String email, String telefono, Integer areaId) {}
}
