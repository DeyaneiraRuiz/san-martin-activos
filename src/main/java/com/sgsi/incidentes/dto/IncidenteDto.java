package com.sgsi.incidentes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Set;

public interface IncidenteDto {

    record Request(

            // DATOS GENERALES
            String codigo,

            @NotBlank(message = "El título es obligatorio")
            String titulo,

            String descripcion,

            @NotNull(message = "La categoría es obligatoria")
            Integer categoriaId,

            @NotNull(message = "El tipo de incidente es obligatorio")
            Integer tipoIncidenteId,

            @NotNull(message = "La gravedad es obligatoria")
            Integer gravedadReportadaId,

            LocalDateTime fechaDeteccion,

            @NotNull(message = "El reportador es obligatorio")
            Integer reportadorId,

            // SEGUIMIENTO
            Integer asignadoAId,

            Integer asignadoPorId,

            LocalDateTime fechaAsignacion,

            Integer impactoRealId,

            Integer prioridadId,

            String causaRaiz,

            String solucionPropuesta,

            String planAccion,

            LocalDateTime fechaCompromiso,

            String descripcionSolucion,

            LocalDateTime fechaResolucion,

            // CIERRE
            String comentarioCierre,

            LocalDateTime fechaCierre,

            Integer cerradoPorId,

            // ESTADO
            @NotNull(message = "El estado es obligatorio")
            Integer estadoId,

            // REAPERTURA
            Boolean reabierto,

            String motivoReapertura,

            Integer reabiertoPorId,

            LocalDateTime fechaReapertura,

            // RELACIONES
            Set<Integer> observadorIds,

            Set<Integer> activoIds

    ) {
    }

    record Response(

            Integer id,

            // DATOS GENERALES
            String codigo,
            String titulo,
            String descripcion,

            Integer categoriaId,
            String categoriaNombre,

            Integer tipoIncidenteId,
            String tipoIncidenteNombre,

            Integer gravedadReportadaId,
            String gravedadReportadaNombre,

            LocalDateTime fechaDeteccion,
            LocalDateTime fechaReporte,

            Integer reportadorId,
            String reportadorNombre,

            // SEGUIMIENTO
            Integer asignadoAId,
            String asignadoANombre,

            Integer asignadoPorId,
            String asignadoPorNombre,

            LocalDateTime fechaAsignacion,

            Integer impactoRealId,
            String impactoRealNombre,

            Integer prioridadId,
            String prioridadNombre,

            String causaRaiz,
            String solucionPropuesta,
            String planAccion,

            LocalDateTime fechaCompromiso,

            String descripcionSolucion,

            LocalDateTime fechaResolucion,

            // CIERRE
            String comentarioCierre,

            LocalDateTime fechaCierre,

            Integer cerradoPorId,
            String cerradoPorNombre,

            // ESTADO
            Integer estadoId,
            String estadoNombre,

            // REAPERTURA
            Boolean reabierto,

            String motivoReapertura,

            Integer reabiertoPorId,
            String reabiertoPorNombre,

            LocalDateTime fechaReapertura,

            // AUDITORÍA
            LocalDateTime ultimaModificacion,

            Integer ultimoModificadoPorId,
            String ultimoModificadoPorNombre,

            LocalDateTime createdAt,
            LocalDateTime updatedAt,

            // RELACIONES
            Set<Integer> observadorIds,
            Set<Integer> activoIds

    ) {
    }
}