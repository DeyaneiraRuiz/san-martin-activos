package com.sgsi.riesgos.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface RiesgoDto {
    record Request(
            String nombre,
            @NotBlank(message = "La descripción es obligatoria") String descripcion,
            String impactoNegocio,
            String consecuencia,
            String impacto,
            String frecuencia,
            Integer criticidadInherente,
            Integer efectividadControles,
            Integer criticidadResidual,
            String categoria,
            String estado,
            LocalDate fechaIdentificacion,
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
            LocalDate fechaIdentificacion,
            String archivoNombre,
            Integer amenazaId,
            Integer vulnerabilidadId,
            LocalDateTime createdAt
    ) {}
}
