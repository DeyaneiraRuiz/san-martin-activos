package com.sgsi.incidentes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public interface IncidenteHistorialDto {
    record Request(
            @NotNull(message = "El incidente es obligatorio") Integer incidenteId,
            @NotNull(message = "El usuario modificador es obligatorio") Integer modificadoPorId,
            @NotBlank(message = "La acción es obligatoria") String accion,
            String estadoAnteriorNombre,
            String estadoNuevoNombre,
            String notas
    ) {}

    record Response(
            Integer id,
            Integer incidenteId,
            Integer modificadoPorId,
            String accion,
            String estadoAnteriorNombre,
            String estadoNuevoNombre,
            String notas,
            LocalDateTime fechaModificacion
    ) {}
}
