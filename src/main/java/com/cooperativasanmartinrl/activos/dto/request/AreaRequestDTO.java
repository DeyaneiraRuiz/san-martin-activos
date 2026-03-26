package com.cooperativasanmartinrl.activos.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AreaRequestDTO {
    @NotBlank
    private String nombre;
}