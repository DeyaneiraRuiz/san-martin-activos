package com.sgsi.catalogos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface CatNivelDto {
    record Request(
            @NotNull Integer tipoNivelId,
            @NotBlank String nombre,
            @NotNull Integer valor,
            String descripcion
    ) {}
    record Response(Integer id, Integer tipoNivelId, String nombre, Integer valor, String descripcion) {}
}
