package com.cooperativasanmartinrl.activos.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TratamientoRiesgoRequestDTO {

    @NotBlank
    private String descripcion;

    private String tipo; // mitigación, aceptación, transferencia, etc.
}