package com.cooperativasanmartinrl.activos.dto.response;

import lombok.Data;

@Data
public class EvaluacionRiesgoResponseDTO {

    private Long id;
    private String riesgo;
    private Integer impacto;
    private Integer probabilidad;
    private String nivelRiesgo;
}
