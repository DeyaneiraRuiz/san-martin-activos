package com.sgsi.incidentes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public interface IncidenteEvidenciaDto {

    record Request(

            @NotNull(message = "El incidente es obligatorio")
            Integer incidenteId,

            @NotBlank(message = "El nombre del archivo es obligatorio")
            String nombreArchivo,

            @NotBlank(message = "La URL del archivo es obligatoria")
            String archivoUrl,

            String tipoArchivo,

            Integer tamaño,

            Integer createdBy

    ) {}

    record Response(

            Integer id,

            Integer incidenteId,

            String nombreArchivo,

            String archivoUrl,

            String tipoArchivo,

            Integer tamaño,

            Integer createdBy,

            LocalDateTime createdAt

    ) {}
}