package com.cooperativasanmartinrl.activos.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RiesgoRequestDTO {

    @NotNull
    private Long activoId;

    @NotNull
    private Long procesoId;

    @NotNull
    private Long amenazaId;

    @NotNull
    private Long vulnerabilidadId;

    private String descripcion;
    private String tipoTratamiento;
}
