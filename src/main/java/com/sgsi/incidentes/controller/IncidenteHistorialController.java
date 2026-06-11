package com.sgsi.incidentes.controller;

import com.sgsi.incidentes.dto.IncidenteHistorialDto;
import com.sgsi.incidentes.entity.IncidenteHistorial;
import com.sgsi.incidentes.mapper.IncidentesMapper;
import com.sgsi.incidentes.service.IncidenteHistorialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidente-historial")
@RequiredArgsConstructor
public class IncidenteHistorialController {

    private final IncidenteHistorialService historialService;
    private final IncidentesMapper mapper;

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','TECNICO','ANALISTA_SEGURIDAD')")
    @GetMapping
    public ResponseEntity<List<IncidenteHistorialDto.Response>> findAll() {

        List<IncidenteHistorialDto.Response> response = historialService.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','TECNICO','ANALISTA_SEGURIDAD')")
    @GetMapping("/{id}")
    public ResponseEntity<IncidenteHistorialDto.Response> findById(
            @PathVariable Integer id) {

        return historialService.findById(id)
                .map(mapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','TECNICO')")
    @PostMapping
    public ResponseEntity<IncidenteHistorialDto.Response> create(
            @Valid @RequestBody IncidenteHistorialDto.Request request) {

        IncidenteHistorial historial = mapper.toEntity(request);

        IncidenteHistorial saved = historialService.crear(historial);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toResponse(saved));
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','TECNICO')")
    @PutMapping("/{id}")
    public ResponseEntity<IncidenteHistorialDto.Response> update(
            @PathVariable Integer id,
            @Valid @RequestBody IncidenteHistorialDto.Request request) {

        return historialService.findById(id)
                .map(existing -> {

                    mapper.updateEntityFromRequest(request, existing);

                    IncidenteHistorial updated =
                            historialService.actualizar(existing);

                    return ResponseEntity.ok(
                            mapper.toResponse(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer id) {

        if (historialService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        historialService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}