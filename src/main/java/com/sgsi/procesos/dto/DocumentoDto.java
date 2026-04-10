package com.sgsi.procesos.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public interface DocumentoDto {
    record Request(
            @NotBlank(message = "El nombre es obligatorio") String nombre,
            String url,
            String version,
            String estado
    ) {}

    record Response(
            Integer id,
            String nombre,
            String url,
            String version,
            String estado,
            LocalDateTime createdAt
    ) {}
}
