package com.sgsi.procesos.controller;

import com.sgsi.procesos.dto.ActivoProcesoDto;
import com.sgsi.procesos.entity.ActivoProceso;
import com.sgsi.procesos.mapper.ProcesosMapper;
import com.sgsi.procesos.service.ActivoProcesoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/activo-procesos")
@RequiredArgsConstructor
public class ActivoProcesoController {

    private final ActivoProcesoService service;
    private final ProcesosMapper mapper;

    @GetMapping
    public ResponseEntity<List<ActivoProcesoDto.Response>> findAll() {
        return ResponseEntity.ok(service.findAll().stream()
                .map(mapper::toResponse).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivoProcesoDto.Response> findById(@PathVariable Integer id) {
        return service.findById(id).map(mapper::toResponse)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ActivoProcesoDto.Response> create(@Valid @RequestBody ActivoProcesoDto.Request request) {
        ActivoProceso saved = service.save(mapper.toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActivoProcesoDto.Response> update(@PathVariable Integer id, @Valid @RequestBody ActivoProcesoDto.Request request) {
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
