package com.sgsi.archivos.dto;

import java.time.LocalDateTime;

public class ArchivoDto {

    public record Response(
            Long id,
            String nombre,
            String tipoMime,
            Long tamanio,
            LocalDateTime creadoEn
    ) {}
}
