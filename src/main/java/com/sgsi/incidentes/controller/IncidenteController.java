package com.sgsi.incidentes.controller;

import com.sgsi.incidentes.dto.IncidenteDto;
import com.sgsi.incidentes.entity.Incidente;
import com.sgsi.incidentes.mapper.IncidentesMapper;
import com.sgsi.incidentes.service.IncidenteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/incidentes")
@RequiredArgsConstructor
public class IncidenteController {

    private final IncidenteService service;
    private final IncidentesMapper mapper;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<IncidenteDto.Response>> findAll() {
        return ResponseEntity.ok(service.findAll().stream()
                .map(mapper::toResponse).collect(Collectors.toList()));
    }

    @PreAuthorize("hasRole('ADMINISTRADOR') or @incidenteSecurity.canRead(authentication, #id)")
    @GetMapping("/{id}")
    public ResponseEntity<IncidenteDto.Response> findById(@PathVariable Integer id) {
        return service.findById(id).map(mapper::toResponse)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'USUARIO')")
    @PostMapping
    public ResponseEntity<IncidenteDto.Response> create(@Valid @RequestBody IncidenteDto.Request request) {
        Incidente saved = service.save(mapper.toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(saved));
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'TECNICO')")
    @PutMapping("/{id}")
    public ResponseEntity<IncidenteDto.Response> update(@PathVariable Integer id, @Valid @RequestBody IncidenteDto.Request request) {
        return service.findById(id).map(existing -> {
            mapper.updateEntityFromRequest(request, existing);
            return ResponseEntity.ok(mapper.toResponse(service.save(existing)));
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
}
