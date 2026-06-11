package com.sgsi.incidentes.controller;

import com.sgsi.incidentes.dto.IncidenteEvidenciaDto;
import com.sgsi.incidentes.entity.IncidenteEvidencia;
import com.sgsi.incidentes.mapper.IncidentesMapper;
import com.sgsi.incidentes.service.IncidenteEvidenciaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidente-evidencias")
@RequiredArgsConstructor
public class IncidenteEvidenciaController {

    private final IncidenteEvidenciaService evidenciaService;
    private final IncidentesMapper mapper;

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','TECNICO','ANALISTA_SEGURIDAD')")
    @GetMapping
    public ResponseEntity<List<IncidenteEvidenciaDto.Response>> findAll() {

        List<IncidenteEvidenciaDto.Response> response =
                evidenciaService.findAll()
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','TECNICO','ANALISTA_SEGURIDAD')")
    @GetMapping("/{id}")
    public ResponseEntity<IncidenteEvidenciaDto.Response> findById(
            @PathVariable Integer id) {

        return evidenciaService.findById(id)
                .map(mapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','TECNICO')")
    @PostMapping
    public ResponseEntity<IncidenteEvidenciaDto.Response> create(
            @Valid @RequestBody IncidenteEvidenciaDto.Request request) {

        IncidenteEvidencia evidencia =
                mapper.toEntity(request);

        IncidenteEvidencia saved =
                evidenciaService.crear(evidencia);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toResponse(saved));
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','TECNICO')")
    @PutMapping("/{id}")
    public ResponseEntity<IncidenteEvidenciaDto.Response> update(
            @PathVariable Integer id,
            @Valid @RequestBody IncidenteEvidenciaDto.Request request) {

        return evidenciaService.findById(id)
                .map(existing -> {

                    mapper.updateEntityFromRequest(
                            request,
                            existing
                    );

                    IncidenteEvidencia updated =
                            evidenciaService.actualizar(existing);

                    return ResponseEntity.ok(
                            mapper.toResponse(updated)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer id) {

        if (evidenciaService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        evidenciaService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}