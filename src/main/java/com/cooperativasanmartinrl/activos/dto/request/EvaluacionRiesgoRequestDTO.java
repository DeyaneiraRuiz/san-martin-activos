package com.cooperativasanmartinrl.activos.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EvaluacionRiesgoRequestDTO {

    @NotNull
    private Long riesgoId;

    private Integer impacto;
    private Integer probabilidad;

    @NotNull
    private Long nivelRiesgoId;
}
