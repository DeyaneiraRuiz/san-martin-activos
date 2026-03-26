package com.cooperativasanmartinrl.activos.dto.response;

import lombok.Data;

@Data
public class RiesgoResponseDTO {

    private Long id;

    private String activo;
    private String proceso;
    private String amenaza;
    private String vulnerabilidad;

    private String descripcion;
    private String tipoTratamiento;
}
