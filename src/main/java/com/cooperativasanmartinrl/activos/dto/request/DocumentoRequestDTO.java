package com.cooperativasanmartinrl.activos.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DocumentoRequestDTO {
    @NotBlank
    private String nombre;
    private String url;
}
