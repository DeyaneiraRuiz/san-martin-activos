package com.cooperativasanmartinrl.activos.dto.request;

import lombok.Data;

@Data
public class NivelRiesgoRequestDTO {
    private String nombre;
    private Integer valorMin;
    private Integer valorMax;
}
