package com.sgsi.incidentes.controller;

import com.sgsi.incidentes.dto.CategoriaIncidenteDto;
import com.sgsi.incidentes.entity.CategoriaIncidente;
import com.sgsi.incidentes.mapper.IncidentesMapper;
import com.sgsi.incidentes.service.CategoriaIncidenteService;
import com.sgsi.security.entity.Usuario;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria-incidentes")
@RequiredArgsConstructor
public class CategoriaIncidenteController {

    private final CategoriaIncidenteService service;
    private final IncidentesMapper mapper;

    @GetMapping
    public ResponseEntity<List<CategoriaIncidenteDto.Response>> findAll() {
        List<CategoriaIncidenteDto.Response> response = service.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaIncidenteDto.Response> findById(@PathVariable Integer id) {
        return service.findById(id)
                .map(mapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoriaIncidenteDto.Response> create(
            @Valid @RequestBody CategoriaIncidenteDto.Request request) {

        CategoriaIncidente entity = mapper.toEntity(request);
        CategoriaIncidente saved = service.crear(entity);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toResponse(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaIncidenteDto.Response> update(
            @PathVariable Integer id,
            @Valid @RequestBody CategoriaIncidenteDto.Request request) {

        return service.findById(id)
                .map(existing -> {

                    // Actualizar campos simples
                    existing.setNombre(request.nombre());
                    existing.setDescripcion(request.descripcion());
                    existing.setCodigo(request.codigo());
                    existing.setEstado(request.estado() != null ? request.estado() : "activo");

                    // ✅ CORRECCIÓN CRÍTICA: Manejar responsable de forma segura
                    if (request.responsableId() != null) {
                        Usuario nuevoResponsable = service.getUsuarioById(request.responsableId())
                                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + request.responsableId()));
                        existing.setResponsable(nuevoResponsable);
                    } else {
                        existing.setResponsable(null);
                    }

                    CategoriaIncidente updated = service.actualizar(existing);

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