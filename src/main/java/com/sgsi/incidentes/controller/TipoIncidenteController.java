package com.sgsi.incidentes.controller;

import com.sgsi.incidentes.dto.TipoIncidenteDto;
import com.sgsi.incidentes.entity.TipoIncidente;
import com.sgsi.incidentes.service.TipoIncidenteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tipo-incidentes")
@RequiredArgsConstructor
public class TipoIncidenteController {

    private final TipoIncidenteService service;

    @GetMapping
    public ResponseEntity<List<TipoIncidenteDto.Response>> findAll() {
        return ResponseEntity.ok(service.findAll().stream()
                .map(this::toResponse).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoIncidenteDto.Response> findById(@PathVariable Integer id) {
        return service.findById(id).map(this::toResponse)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'ANALISTA_SEGURIDAD')")
    @PostMapping
    public ResponseEntity<TipoIncidenteDto.Response> create(@Valid @RequestBody TipoIncidenteDto.Request request) {
        TipoIncidente entity = toEntity(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(service.save(entity)));
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'ANALISTA_SEGURIDAD')")
    @PutMapping("/{id}")
    public ResponseEntity<TipoIncidenteDto.Response> update(@PathVariable Integer id,
                                                             @Valid @RequestBody TipoIncidenteDto.Request request) {
        return service.findById(id).map(existing -> {
            existing.setNombre(request.nombre());
            existing.setEstado(request.estado() != null ? request.estado() : existing.getEstado());
            if (request.categoriaId() != null) {
                com.sgsi.incidentes.entity.CategoriaIncidente cat = new com.sgsi.incidentes.entity.CategoriaIncidente();
                cat.setId(request.categoriaId());
                existing.setCategoria(cat);
            }
            return ResponseEntity.ok(toResponse(service.save(existing)));
        }).orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (service.findById(id).isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private TipoIncidenteDto.Response toResponse(TipoIncidente e) {
        return new TipoIncidenteDto.Response(
                e.getId(), e.getCodigo(), e.getNombre(),
                e.getCategoria() != null ? e.getCategoria().getId() : null,
                e.getEstado(), e.getCreadoPor(), e.getModificadoPor(),
                e.getCreatedAt(), e.getUpdatedAt());
    }

    private TipoIncidente toEntity(TipoIncidenteDto.Request req) {
        TipoIncidente e = new TipoIncidente();
        e.setNombre(req.nombre());
        e.setEstado(req.estado() != null ? req.estado() : "activo");
        if (req.categoriaId() != null) {
            com.sgsi.incidentes.entity.CategoriaIncidente cat = new com.sgsi.incidentes.entity.CategoriaIncidente();
            cat.setId(req.categoriaId());
            e.setCategoria(cat);
        }
        return e;
    }
}