package com.sgsi.incidentes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public interface IncidenteDto {
    record Request(
            @NotBlank(message = "El código es obligatorio") String codigo,
            @NotBlank(message = "El título es obligatorio") String titulo,
            String descripcion,
            @NotNull(message = "La categoría es obligatoria") Integer categoriaId,
            @NotNull(message = "El estado es obligatorio") Integer estadoId,
            @NotNull(message = "El tipo de ticket es obligatorio") Integer tipoTicketId,
            @NotNull(message = "El reportador es obligatorio") Integer reportadoPorId,
            Integer asignadoAId,
            LocalDateTime fechaIncidente,
            LocalDateTime fechaEstimadaResolucion,
            LocalDateTime fechaRealResolucion,
            String notasResolucion
    ) {}

    record Response(
            Integer id,
            String codigo,
            String titulo,
            String descripcion,
            Integer categoriaId,
            Integer estadoId,
            Integer tipoTicketId,
            Integer reportadoPorId,
            Integer asignadoAId,
            LocalDateTime fechaIncidente,
            LocalDateTime fechaEstimadaResolucion,
            LocalDateTime fechaRealResolucion,
            String notasResolucion,
            LocalDateTime createdAt
    ) {}
}
