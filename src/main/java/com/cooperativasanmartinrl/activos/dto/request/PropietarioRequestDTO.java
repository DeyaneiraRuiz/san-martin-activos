package com.cooperativasanmartinrl.activos.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PropietarioRequestDTO {

    @NotBlank
    private String nombre;

    private String cargo;

    @NotNull
    private Long areaId;
}