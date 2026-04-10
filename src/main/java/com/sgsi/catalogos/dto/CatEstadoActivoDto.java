package com.sgsi.catalogos.dto;

import jakarta.validation.constraints.NotBlank;

public interface CatEstadoActivoDto {
    record Request(@NotBlank(message = "El nombre es obligatorio") String nombre, String descripcion) {}
    record Response(Integer id, String nombre, String descripcion) {}
}
