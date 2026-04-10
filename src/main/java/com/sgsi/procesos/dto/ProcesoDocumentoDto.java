package com.sgsi.procesos.dto;

import jakarta.validation.constraints.NotNull;

public interface ProcesoDocumentoDto {
    record Request(
            @NotNull(message = "El proceso es obligatorio") Integer procesoId,
            @NotNull(message = "El documento es obligatorio") Integer documentoId,
            Boolean esObligatorio
    ) {}
    record Response(Integer id, Integer procesoId, Integer documentoId, Boolean esObligatorio) {}
}
