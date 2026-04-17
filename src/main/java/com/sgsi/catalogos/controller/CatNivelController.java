package com.sgsi.catalogos.controller;

import com.sgsi.catalogos.dto.CatNivelDto;
import com.sgsi.catalogos.entity.CatNivel;
import com.sgsi.catalogos.mapper.CatalogosMapper;
import com.sgsi.catalogos.service.CatNivelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cat-nivels")
public class CatNivelController {

    @Autowired
    private CatNivelService service;

    @Autowired
    private CatalogosMapper mapper;

    @GetMapping
    public ResponseEntity<List<CatNivelDto.Response>> getAll() {
        return ResponseEntity.ok(service.findAll().stream().map(mapper::toResponse).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatNivelDto.Response> getById(@PathVariable Integer id) {
        return service.findById(id)
                .map(entity -> ResponseEntity.ok(mapper.toResponse(entity)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CatNivelDto.Response> create(@Valid @RequestBody CatNivelDto.Request request) {
        CatNivel entity = mapper.toEntity(request);
        return ResponseEntity.ok(mapper.toResponse(service.save(entity)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CatNivelDto.Response> update(@PathVariable Integer id, @Valid @RequestBody CatNivelDto.Request request) {
        return service.findById(id)
                .map(existing -> {
                    CatNivel entity = mapper.toEntity(request);
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
