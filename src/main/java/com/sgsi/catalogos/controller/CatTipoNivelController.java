package com.sgsi.catalogos.controller;

import com.sgsi.catalogos.dto.CatTipoNivelDto;
import com.sgsi.catalogos.entity.CatTipoNivel;
import com.sgsi.catalogos.mapper.CatalogosMapper;
import com.sgsi.catalogos.service.CatTipoNivelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cat-tipo-nivels")
public class CatTipoNivelController {

    @Autowired
    private CatTipoNivelService service;

    @Autowired
    private CatalogosMapper mapper;

    @GetMapping
    public ResponseEntity<List<CatTipoNivelDto.Response>> getAll() {
        return ResponseEntity.ok(service.findAll().stream().map(mapper::toResponse).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatTipoNivelDto.Response> getById(@PathVariable Integer id) {
        return service.findById(id)
                .map(entity -> ResponseEntity.ok(mapper.toResponse(entity)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CatTipoNivelDto.Response> create(@Valid @RequestBody CatTipoNivelDto.Request request) {
        CatTipoNivel entity = mapper.toEntity(request);
        return ResponseEntity.ok(mapper.toResponse(service.save(entity)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CatTipoNivelDto.Response> update(@PathVariable Integer id, @Valid @RequestBody CatTipoNivelDto.Request request) {
        return service.findById(id)
                .map(existing -> {
                    CatTipoNivel entity = mapper.toEntity(request);
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
