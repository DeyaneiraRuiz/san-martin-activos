package com.sgsi.catalogos.controller;

import com.sgsi.catalogos.dto.CatEstadoIncidenteDto;
import com.sgsi.catalogos.entity.CatEstadoIncidente;
import com.sgsi.catalogos.mapper.CatalogosMapper;
import com.sgsi.catalogos.service.CatEstadoIncidenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cat-estado-incidentes")
public class CatEstadoIncidenteController {

    @Autowired
    private CatEstadoIncidenteService service;

    @Autowired
    private CatalogosMapper mapper;

    @GetMapping
    public ResponseEntity<List<CatEstadoIncidenteDto.Response>> getAll() {
        return ResponseEntity.ok(service.findAll().stream().map(mapper::toResponse).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatEstadoIncidenteDto.Response> getById(@PathVariable Integer id) {
        return service.findById(id)
                .map(entity -> ResponseEntity.ok(mapper.toResponse(entity)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CatEstadoIncidenteDto.Response> create(@Valid @RequestBody CatEstadoIncidenteDto.Request request) {
        CatEstadoIncidente entity = mapper.toEntity(request);
        return ResponseEntity.ok(mapper.toResponse(service.save(entity)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CatEstadoIncidenteDto.Response> update(@PathVariable Integer id, @Valid @RequestBody CatEstadoIncidenteDto.Request request) {
        return service.findById(id)
                .map(existing -> {
                    CatEstadoIncidente entity = mapper.toEntity(request);
                    entity.setId(id);
                    return ResponseEntity.ok(mapper.toResponse(service.save(entity)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
