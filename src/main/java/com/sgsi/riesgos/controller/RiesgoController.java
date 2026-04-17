package com.sgsi.riesgos.controller;

import com.sgsi.riesgos.dto.RiesgoDto;
import com.sgsi.riesgos.entity.Riesgo;
import com.sgsi.riesgos.mapper.RiesgosMapper;
import com.sgsi.riesgos.service.RiesgoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/riesgos")
@RequiredArgsConstructor
public class RiesgoController {

    private final RiesgoService service;
    private final RiesgosMapper mapper;

    @GetMapping
    public ResponseEntity<List<RiesgoDto.Response>> findAll() {
        return ResponseEntity.ok(service.findAll().stream()
                .map(mapper::toResponse).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RiesgoDto.Response> findById(@PathVariable Integer id) {
        return service.findById(id).map(mapper::toResponse)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RiesgoDto.Response> create(@Valid @RequestBody RiesgoDto.Request request) {
        Riesgo saved = service.save(mapper.toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RiesgoDto.Response> update(@PathVariable Integer id, @Valid @RequestBody RiesgoDto.Request request) {
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
