package com.sgsi.procesos.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public interface ControlDto {
    record Request(
            String codigoIso,
            @NotBlank(message = "El nombre es obligatorio") String nombre,
            String descripcion,
            String tipoControl,
            String tipoEjecucion,
            Boolean seEjecutaConFrecuencia,
            String estaDocumentado,
            Boolean tieneEvidencia,
            Boolean tieneResponsablesAsociados,
            Integer calificacionDisenio,
            Boolean seHanPresentadoEventos,
            Boolean disenioEsEfectivo,
            Boolean evidenciaEsEfectiva,
            Integer calificacionEjecucion,
            Integer solidez,
            String archivoNombre,
            String estado
    ) {}

    record Response(
            Integer id,
            String codigo,
            String codigoIso,
            String nombre,
            String descripcion,
            String tipoControl,
            String tipoEjecucion,
            Boolean seEjecutaConFrecuencia,
            String estaDocumentado,
            Boolean tieneEvidencia,
            Boolean tieneResponsablesAsociados,
            Integer calificacionDisenio,
            Boolean seHanPresentadoEventos,
            Boolean disenioEsEfectivo,
            Boolean evidenciaEsEfectiva,
            Integer calificacionEjecucion,
            Integer solidez,
            String archivoNombre,
            String estado,
            String creadoPor,
            String modificadoPor,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {}
}
