package com.sgsi.riesgos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface RiesgoDto {
    record Request(
            @NotBlank(message = "La descripción es obligatoria") String descripcion,
            String impactoNegocio,
            String consecuencia,
            @NotNull(message = "Amenaza obligatoria") Integer amenazaId,
            @NotNull(message = "Vulnerabilidad obligatoria") Integer vulnerabilidadId
    ) {}

    record Response(
            Integer id,
            String descripcion,
            String impactoNegocio,
            String consecuencia,
            Integer amenazaId,
            Integer vulnerabilidadId
    ) {}
}
