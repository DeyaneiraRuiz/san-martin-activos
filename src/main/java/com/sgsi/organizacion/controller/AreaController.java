package com.sgsi.organizacion.controller;

import com.sgsi.organizacion.dto.AreaDto;
import com.sgsi.organizacion.entity.Area;
import com.sgsi.organizacion.mapper.OrganizacionMapper;
import com.sgsi.organizacion.service.AreaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/areas")
public class AreaController {

    @Autowired
    private AreaService service;

    @Autowired
    private OrganizacionMapper mapper;

    @GetMapping
    public ResponseEntity<List<AreaDto.Response>> getAll() {
        return ResponseEntity.ok(service.findAll().stream().map(mapper::toResponse).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AreaDto.Response> getById(@PathVariable Integer id) {
        return service.findById(id)
                .map(entity -> ResponseEntity.ok(mapper.toResponse(entity)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AreaDto.Response> create(@Valid @RequestBody AreaDto.Request request) {
        Area entity = mapper.toEntity(request);
        return ResponseEntity.ok(mapper.toResponse(service.save(entity)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AreaDto.Response> update(@PathVariable Integer id, @Valid @RequestBody AreaDto.Request request) {
        return service.findById(id)
                .map(existing -> {
                    Area entity = mapper.toEntity(request);
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
