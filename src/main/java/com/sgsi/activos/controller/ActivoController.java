package com.sgsi.activos.controller;

import com.sgsi.activos.dto.ActivoDto;
import com.sgsi.activos.entity.Activo;
import com.sgsi.activos.mapper.ActivoMapper;
import com.sgsi.activos.service.ActivoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/activos")
@RequiredArgsConstructor
public class ActivoController {

    private final ActivoService service;
    private final ActivoMapper mapper;

    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'RESPONSABLE_AREA', 'ANALISTA_SEGURIDAD')")
    @GetMapping
    public ResponseEntity<List<ActivoDto.Response>> findAll() {
        List<ActivoDto.Response> responses = service.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'RESPONSABLE_AREA', 'ANALISTA_SEGURIDAD')")
    @GetMapping("/{id}")
    public ResponseEntity<ActivoDto.Response> findById(@PathVariable Integer id) {
        return service.findById(id)
                .map(mapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'RESPONSABLE_AREA')")
    @PostMapping
    public ResponseEntity<ActivoDto.Response> create(@Valid @RequestBody ActivoDto.Request request) {
        Activo entity = mapper.toEntity(request);
        Activo savedEntity = service.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(savedEntity));
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'RESPONSABLE_AREA')")
    @PutMapping("/{id}")
    public ResponseEntity<ActivoDto.Response> update(@PathVariable Integer id, @Valid @RequestBody ActivoDto.Request request) {
        return service.update(id, request)
                .map(mapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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
}
