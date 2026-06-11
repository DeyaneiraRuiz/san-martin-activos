package com.sgsi.incidentes.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public interface IncidenteHistorialDto {

    record Request(

            @NotNull(message = "El incidente es obligatorio")
            Integer incidenteId,

            @NotNull(message = "El estado es obligatorio")
            Integer estadoId,

            String comentario,

            Integer changedById,

            LocalDateTime fecha

    ) {}

    record Response(

            Integer id,

            Integer incidenteId,

            Integer estadoId,

            String comentario,

            Integer changedById,

            LocalDateTime fecha

    ) {}
}