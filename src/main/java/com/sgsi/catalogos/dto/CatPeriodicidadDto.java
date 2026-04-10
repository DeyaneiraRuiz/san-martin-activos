package com.sgsi.catalogos.dto;

import jakarta.validation.constraints.NotBlank;

public interface CatPeriodicidadDto {
    record Request(@NotBlank String nombre, Integer dias) {}
    record Response(Integer id, String nombre, Integer dias) {}
}
