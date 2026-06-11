package com.sgsi.incidentes.dto;

import jakarta.validation.constraints.NotNull;

public interface IncidenteVinculoDto {

    record Request(

            @NotNull(message = "El incidente es obligatorio")
            Integer incidenteId,

            @NotNull(message = "El incidente relacionado es obligatorio")
            Integer incidenteRelacionadoId,

            @NotNull(message = "El tipo de vínculo es obligatorio")
            Integer tipoVinculoId

    ) {}

    record Response(

            Integer id,

            Integer incidenteId,

            Integer incidenteRelacionadoId,

            Integer tipoVinculoId

    ) {}
}