package com.cooperativasanmartinrl.activos.dto.response;

import lombok.Data;

@Data
public class ProcesoResponseDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private Boolean esCritico;
}