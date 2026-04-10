package com.sgsi.catalogos.dto;

import jakarta.validation.constraints.NotBlank;

public interface CatEstadoIncidenteDto {
    record Request(@NotBlank String nombre, String descripcion) {}
    record Response(Integer id, String nombre, String descripcion) {}
}
