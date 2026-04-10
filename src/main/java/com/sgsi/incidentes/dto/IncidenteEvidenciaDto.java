package com.sgsi.incidentes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public interface IncidenteEvidenciaDto {
    record Request(
            @NotNull(message = "El incidente es obligatorio") Integer incidenteId,
            @NotBlank(message = "El nombre de archivo es obligatorio") String nombreArchivo,
            @NotBlank(message = "La ruta del archivo es obligatoria") String rutaArchivo,
            String tipoArchivo,
            Integer tamañoBytes,
            @NotNull(message = "El usuario subidor es obligatorio") Integer subidoPorId,
            String descripcion
    ) {}

    record Response(
            Integer id,
            Integer incidenteId,
            String nombreArchivo,
            String rutaArchivo,
            String tipoArchivo,
            Integer tamañoBytes,
            Integer subidoPorId,
            String descripcion,
            LocalDateTime fechaSubida
    ) {}
}
