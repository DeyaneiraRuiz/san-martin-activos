package com.cooperativasanmartinrl.activos.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ActivoRequestDTO {

    @NotBlank
    private String nombre;

    private String descripcion;

    @NotNull
    private Long tipoActivoId;

    @NotNull
    private Long areaId;

    @NotNull
    private Long propietarioId;

    private String clasificacion;
    private String medio;
    private String origen;
}