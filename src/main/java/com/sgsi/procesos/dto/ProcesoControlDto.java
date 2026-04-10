package com.sgsi.procesos.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public interface ProcesoControlDto {
    record Request(
            @NotNull(message = "El proceso es obligatorio") Integer procesoId,
            @NotNull(message = "El control es obligatorio") Integer controlId,
            String estadoImplementacion,
            LocalDate fechaRevision
    ) {}
    record Response(Integer id, Integer procesoId, Integer controlId, String estadoImplementacion, LocalDate fechaRevision) {}
}
