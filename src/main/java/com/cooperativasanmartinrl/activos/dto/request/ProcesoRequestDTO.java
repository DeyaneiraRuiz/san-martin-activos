package com.cooperativasanmartinrl.activos.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProcesoRequestDTO {

    @NotBlank
    private String nombre;

    private String descripcion;
    private String periodicidad;
    private String nivelAutomatizacion;
    private String gradoDescentralizacion;
    private String tiempoProceso;
    private Boolean esCritico;
    private String productoServicio;
}
