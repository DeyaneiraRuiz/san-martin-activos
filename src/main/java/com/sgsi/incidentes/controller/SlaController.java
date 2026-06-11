package com.sgsi.incidentes.controller;

import com.sgsi.incidentes.dto.SlaDto;
import com.sgsi.incidentes.entity.Sla;
import com.sgsi.incidentes.mapper.IncidentesMapper;
import com.sgsi.incidentes.service.SlaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/slas")
@RequiredArgsConstructor
public class SlaController {

    private final SlaService slaService;
    private final IncidentesMapper mapper;

    @GetMapping
    public ResponseEntity<List<SlaDto.Response>> findAll() {

        List<SlaDto.Response> response =
                slaService.findAll()
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SlaDto.Response> findById(
            @PathVariable Integer id) {

        return slaService.findById(id)
                .map(mapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','ANALISTA_SEGURIDAD')")
    @PostMapping
    public ResponseEntity<SlaDto.Response> create(
            @Valid @RequestBody SlaDto.Request request) {

        Sla entity = mapper.toEntity(request);

        Sla saved = slaService.crear(entity);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toResponse(saved));
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','ANALISTA_SEGURIDAD')")
    @PutMapping("/{id}")
    public ResponseEntity<SlaDto.Response> update(
            @PathVariable Integer id,
            @Valid @RequestBody SlaDto.Request request) {

        return slaService.findById(id)
                .map(existing -> {

                    mapper.updateEntityFromRequest(
                            request,
                            existing
                    );

                    Sla updated =
                            slaService.actualizar(existing);

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

        if (slaService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        slaService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}