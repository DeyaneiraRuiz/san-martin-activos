package com.cooperativasanmartinrl.activos.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ActivoResponseDTO {

    private Long id;
    private String nombre;
    private String descripcion;

    private String tipoActivo;
    private String area;
    private String propietario;

    private String clasificacion;
    private String medio;
    private String origen;

    private LocalDateTime createdAt;
}