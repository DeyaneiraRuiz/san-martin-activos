package com.sgsi.organizacion.controller;

import com.sgsi.organizacion.dto.PropietarioDto;
import com.sgsi.organizacion.entity.Propietario;
import com.sgsi.organizacion.mapper.OrganizacionMapper;
import com.sgsi.organizacion.service.PropietarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/propietarios")
public class PropietarioController {

    @Autowired
    private PropietarioService service;

    @Autowired
    private OrganizacionMapper mapper;

    @GetMapping
    public ResponseEntity<List<PropietarioDto.Response>> getAll() {
        return ResponseEntity.ok(service.findAll().stream().map(mapper::toResponse).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropietarioDto.Response> getById(@PathVariable Integer id) {
        return service.findById(id)
                .map(entity -> ResponseEntity.ok(mapper.toResponse(entity)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PropietarioDto.Response> create(@Valid @RequestBody PropietarioDto.Request request) {
        Propietario entity = mapper.toEntity(request);
        return ResponseEntity.ok(mapper.toResponse(service.save(entity)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropietarioDto.Response> update(@PathVariable Integer id, @Valid @RequestBody PropietarioDto.Request request) {
        return service.findById(id)
                .map(existing -> {
                    Propietario entity = mapper.toEntity(request);
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
