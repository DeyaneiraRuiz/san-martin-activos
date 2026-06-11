package com.sgsi.procesos.controller;

import com.sgsi.procesos.dto.ControlDto;
import com.sgsi.procesos.entity.Control;
import com.sgsi.procesos.mapper.ProcesosMapper;
import com.sgsi.procesos.service.ControlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@PreAuthorize("hasAnyRole('ADMINISTRADOR', 'ANALISTA_SEGURIDAD')")
@RestController
@RequestMapping("/api/controles")
@RequiredArgsConstructor
public class ControlController {

    private final ControlService service;
    private final ProcesosMapper mapper;

    @GetMapping
    public ResponseEntity<List<ControlDto.Response>> findAll() {
        return ResponseEntity.ok(service.findAll().stream()
                .map(mapper::toResponse).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ControlDto.Response> findById(@PathVariable Integer id) {
        return service.findById(id).map(mapper::toResponse)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ControlDto.Response> create(@Valid @RequestBody ControlDto.Request request) {
        Control saved = service.save(mapper.toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ControlDto.Response> update(@PathVariable Integer id, @Valid @RequestBody ControlDto.Request request) {
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
