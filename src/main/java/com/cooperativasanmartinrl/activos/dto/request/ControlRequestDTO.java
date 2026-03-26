package com.cooperativasanmartinrl.activos.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ControlRequestDTO {
    @NotBlank
    private String descripcion;
    private String tipo;
}
