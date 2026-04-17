package com.sgsi.incidentes.controller;

import com.sgsi.incidentes.dto.IncidenteEvidenciaDto;
import com.sgsi.incidentes.entity.IncidenteEvidencia;
import com.sgsi.incidentes.mapper.IncidentesMapper;
import com.sgsi.incidentes.service.IncidenteEvidenciaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/incidente-evidencias")
@RequiredArgsConstructor
public class IncidenteEvidenciaController {

    private final IncidenteEvidenciaService service;
    private final IncidentesMapper mapper;

    @GetMapping
    public ResponseEntity<List<IncidenteEvidenciaDto.Response>> findAll() {
        return ResponseEntity.ok(service.findAll().stream()
                .map(mapper::toResponse).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncidenteEvidenciaDto.Response> findById(@PathVariable Integer id) {
        return service.findById(id).map(mapper::toResponse)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<IncidenteEvidenciaDto.Response> create(@Valid @RequestBody IncidenteEvidenciaDto.Request request) {
        IncidenteEvidencia saved = service.save(mapper.toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IncidenteEvidenciaDto.Response> update(@PathVariable Integer id, @Valid @RequestBody IncidenteEvidenciaDto.Request request) {
        return service.findById(id).map(existing -> {
            mapper.updateEntityFromRequest(request, existing);
            return ResponseEntity.ok(mapper.toResponse(service.save(existing)));
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
}
