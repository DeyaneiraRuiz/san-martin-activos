package com.cooperativasanmartinrl.activos.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProcesoDocumentoRequestDTO {

    @NotNull
    private Long procesoId;

    @NotNull
    private Long documentoId;
}