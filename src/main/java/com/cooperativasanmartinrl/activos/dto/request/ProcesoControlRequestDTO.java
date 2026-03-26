package com.cooperativasanmartinrl.activos.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProcesoControlRequestDTO {

    @NotNull
    private Long procesoId;

    @NotNull
    private Long controlId;
}