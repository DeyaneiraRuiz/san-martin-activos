package com.sgsi.riesgos.controller;

import com.sgsi.riesgos.dto.AmenazaDto;
import com.sgsi.riesgos.entity.Amenaza;
import com.sgsi.riesgos.mapper.RiesgosMapper;
import com.sgsi.riesgos.service.AmenazaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/amenazas")
@RequiredArgsConstructor
public class AmenazaController {

    private final AmenazaService service;
    private final RiesgosMapper mapper;

    @GetMapping
    public ResponseEntity<List<AmenazaDto.Response>> findAll() {
        return ResponseEntity.ok(service.findAll().stream()
                .map(mapper::toResponse).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AmenazaDto.Response> findById(@PathVariable Integer id) {
        return service.findById(id).map(mapper::toResponse)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AmenazaDto.Response> create(@Valid @RequestBody AmenazaDto.Request request) {
        Amenaza saved = service.save(mapper.toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AmenazaDto.Response> update(@PathVariable Integer id, @Valid @RequestBody AmenazaDto.Request request) {
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
