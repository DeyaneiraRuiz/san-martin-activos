package com.sgsi.incidentes.controller;

import com.sgsi.incidentes.dto.IncidenteVinculoDto;
import com.sgsi.incidentes.entity.IncidenteVinculo;
import com.sgsi.incidentes.mapper.IncidentesMapper;
import com.sgsi.incidentes.service.IncidenteVinculoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidente-vinculos")
@RequiredArgsConstructor
public class IncidenteVinculoController {

    private final IncidenteVinculoService vinculoService;
    private final IncidentesMapper mapper;

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','TECNICO','ANALISTA_SEGURIDAD')")
    @GetMapping
    public ResponseEntity<List<IncidenteVinculoDto.Response>> findAll() {

        List<IncidenteVinculoDto.Response> response =
                vinculoService.findAll()
                        .stream()
                        .map(mapper::toResponse)
                        .toList();

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','TECNICO','ANALISTA_SEGURIDAD')")
    @GetMapping("/{id}")
    public ResponseEntity<IncidenteVinculoDto.Response> findById(
            @PathVariable Integer id) {

        return vinculoService.findById(id)
                .map(mapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','TECNICO')")
    @PostMapping
    public ResponseEntity<IncidenteVinculoDto.Response> create(
            @Valid @RequestBody IncidenteVinculoDto.Request request) {

        IncidenteVinculo vinculo =
                mapper.toEntity(request);

        IncidenteVinculo saved =
                vinculoService.crear(vinculo);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toResponse(saved));
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','TECNICO')")
    @PutMapping("/{id}")
    public ResponseEntity<IncidenteVinculoDto.Response> update(
            @PathVariable Integer id,
            @Valid @RequestBody IncidenteVinculoDto.Request request) {

        return vinculoService.findById(id)
                .map(existing -> {

                    mapper.updateEntityFromRequest(
                            request,
                            existing
                    );

                    IncidenteVinculo updated =
                            vinculoService.actualizar(existing);

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

        if (vinculoService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        vinculoService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}