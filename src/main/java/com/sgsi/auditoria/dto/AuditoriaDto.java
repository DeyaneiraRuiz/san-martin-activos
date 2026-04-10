package com.sgsi.auditoria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public interface AuditoriaDto {
    record Request(
            @NotNull(message = "El ID del usuario es obligatorio") Integer usuarioId,
            @NotBlank(message = "La acción es obligatoria") String accion,
            @NotBlank(message = "La tabla afectada es obligatoria") String tablaAfectada,
            Integer registroId,
            String valorAnterior,
            String valorNuevo,
            String ip,
            String modulo,
            String detalle
    ) {}

    record Response(
            Integer id,
            Integer usuarioId,
            String accion,
            String tablaAfectada,
            Integer registroId,
            String valorAnterior,
            String valorNuevo,
            String ip,
            String modulo,
            String detalle,
            LocalDateTime fechaAuditoria
    ) {}
}
