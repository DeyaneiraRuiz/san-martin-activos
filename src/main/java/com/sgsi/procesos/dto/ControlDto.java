package com.sgsi.procesos.dto;

import jakarta.validation.constraints.NotBlank;

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
            String estado,
            String creadoPor,
            String modificadoPor,
            java.time.LocalDateTime createdAt,
            java.time.LocalDateTime updatedAt
    ) {}
}
