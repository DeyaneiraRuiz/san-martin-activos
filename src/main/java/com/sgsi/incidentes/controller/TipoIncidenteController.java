package com.sgsi.incidentes.controller;

import com.sgsi.incidentes.entity.CategoriaIncidente;
import com.sgsi.incidentes.entity.TipoIncidente;
import com.sgsi.incidentes.service.TipoIncidenteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tipo-incidentes")
@RequiredArgsConstructor
public class TipoIncidenteController {

    private final TipoIncidenteService service;

    record TipoIncidenteRequest(
            @NotBlank String nombre,
            Integer categoriaId,
            String estado) {}

    record TipoIncidenteResponse(
            Integer id, String codigo, String nombre,
            Integer categoriaId, String estado,
            String creadoPor, String modificadoPor,
            LocalDateTime createdAt, LocalDateTime updatedAt) {}

    @GetMapping
    public ResponseEntity<List<TipoIncidenteResponse>> findAll() {
        return ResponseEntity.ok(service.findAll().stream()
                .map(this::toResponse).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoIncidenteResponse> findById(@PathVariable Integer id) {
        return service.findById(id).map(this::toResponse)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TipoIncidenteResponse> create(@Valid @RequestBody TipoIncidenteRequest request) {
        TipoIncidente entity = toEntity(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(service.save(entity)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoIncidenteResponse> update(@PathVariable Integer id,
                                                         @Valid @RequestBody TipoIncidenteRequest request) {
        return service.findById(id).map(existing -> {
            existing.setNombre(request.nombre());
            if (request.categoriaId() != null) {
                CategoriaIncidente cat = new CategoriaIncidente();
                cat.setId(request.categoriaId());
                existing.setCategoria(cat);
            }
            if (request.estado() != null) existing.setEstado(request.estado());
            return ResponseEntity.ok(toResponse(service.save(existing)));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (service.findById(id).isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private TipoIncidente toEntity(TipoIncidenteRequest req) {
        TipoIncidente e = new TipoIncidente();
        e.setNombre(req.nombre());
        if (req.categoriaId() != null) {
            CategoriaIncidente cat = new CategoriaIncidente();
            cat.setId(req.categoriaId());
            e.setCategoria(cat);
        }
        e.setEstado(req.estado() != null ? req.estado() : "activo");
        return e;
    }

    private TipoIncidenteResponse toResponse(TipoIncidente e) {
        return new TipoIncidenteResponse(
                e.getId(), e.getCodigo(), e.getNombre(),
                e.getCategoria() != null ? e.getCategoria().getId() : null,
                e.getEstado(), e.getCreadoPor(), e.getModificadoPor(),
                e.getCreatedAt(), e.getUpdatedAt());
    }
}