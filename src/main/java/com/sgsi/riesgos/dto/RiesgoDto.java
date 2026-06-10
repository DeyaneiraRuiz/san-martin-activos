package com.sgsi.riesgos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public interface RiesgoDto {
    record Request(
            @NotBlank(message = "La descripción es obligatoria") String descripcion,
            String nombre,
            String impactoNegocio,
            String consecuencia,
            String impacto,
            String frecuencia,
            Integer criticidadInherente,
            Integer efectividadControles,
            Integer criticidadResidual,
            String categoria,
            String estado,
            String archivoNombre,
            Integer amenazaId,
            Integer vulnerabilidadId
    ) {}

    record Response(
            Integer id,
            String codigo,
            String nombre,
            String descripcion,
            String impactoNegocio,
            String consecuencia,
            String impacto,
            String frecuencia,
            Integer criticidadInherente,
            Integer efectividadControles,
            Integer criticidadResidual,
            String categoria,
            String estado,
            String archivoNombre,
            LocalDateTime fechaIdentificacion,
            LocalDateTime createdAt,
            Integer amenazaId,
            Integer vulnerabilidadId
    ) {}
}
