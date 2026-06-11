package com.sgsi.incidentes.controller;

import com.sgsi.incidentes.dto.TipoIncidenteDto;
import com.sgsi.incidentes.entity.TipoIncidente;
import com.sgsi.incidentes.mapper.IncidentesMapper;
import com.sgsi.incidentes.service.TipoIncidenteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipo-incidentes")
@RequiredArgsConstructor
public class TipoIncidenteController {

    private final TipoIncidenteService service;
    private final IncidentesMapper mapper;

    @GetMapping
    public ResponseEntity<List<TipoIncidenteDto.Response>> findAll() {
        List<TipoIncidenteDto.Response> response = service.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoIncidenteDto.Response> findById(@PathVariable Integer id) {
        return service.findById(id)
                .map(mapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TipoIncidenteDto.Response> create(
            @Valid @RequestBody TipoIncidenteDto.Request request) {

        TipoIncidente entity = mapper.toEntity(request);
        TipoIncidente saved = service.crear(entity);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toResponse(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoIncidenteDto.Response> update(
            @PathVariable Integer id,
            @Valid @RequestBody TipoIncidenteDto.Request request) {

        return service.findById(id)
                .map(existing -> {

                    // Actualizar campos simples
                    existing.setNombre(request.nombre());
                    existing.setEstado(request.estado() != null ? request.estado() : "activo");

                    if (request.categoriaId() != null) {
                        com.sgsi.incidentes.entity.CategoriaIncidente nuevaCategoria = service
                                .getCategoriaById(request.categoriaId())
                                .orElseThrow(() -> new RuntimeException(
                                        "Categoría no encontrada con ID: " + request.categoriaId()));
                        existing.setCategoria(nuevaCategoria);
                    }

                    TipoIncidente updated = service.actualizar(existing);

                    return ResponseEntity.ok(mapper.toResponse(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (service.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}