package com.cooperativasanmartinrl.activos.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClasificacionCIARequestDTO {

    @NotNull
    private Long activoId;

    private Integer confidencialidad;
    private Integer integridad;
    private Integer disponibilidad;
    private Integer criticidad;
}
