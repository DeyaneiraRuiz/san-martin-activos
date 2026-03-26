package com.cooperativasanmartinrl.activos.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RolRequestDTO {
    @NotBlank
    private String nombre;
}
