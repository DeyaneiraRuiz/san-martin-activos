package com.sgsi.incidentes.controller;

import com.sgsi.incidentes.dto.CategoriaIncidenteDto;
import com.sgsi.incidentes.entity.CategoriaIncidente;
import com.sgsi.incidentes.mapper.IncidentesMapper;
import com.sgsi.incidentes.service.CategoriaIncidenteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categoria-incidentes")
@RequiredArgsConstructor
public class CategoriaIncidenteController {

    private final CategoriaIncidenteService service;
    private final IncidentesMapper mapper;

    @GetMapping
    public ResponseEntity<List<CategoriaIncidenteDto.Response>> findAll() {
        return ResponseEntity.ok(service.findAll().stream()
                .map(mapper::toResponse).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaIncidenteDto.Response> findById(@PathVariable Integer id) {
        return service.findById(id).map(mapper::toResponse)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoriaIncidenteDto.Response> create(@Valid @RequestBody CategoriaIncidenteDto.Request request) {
        CategoriaIncidente saved = service.save(mapper.toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaIncidenteDto.Response> update(@PathVariable Integer id, @Valid @RequestBody CategoriaIncidenteDto.Request request) {
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
