package com.sgsi.catalogos.controller;

import com.sgsi.catalogos.dto.CatTipoVinculoDto;
import com.sgsi.catalogos.entity.CatTipoVinculo;
import com.sgsi.catalogos.mapper.CatalogosMapper;
import com.sgsi.catalogos.service.CatTipoVinculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cat-tipo-vinculos")
public class CatTipoVinculoController {

    @Autowired
    private CatTipoVinculoService service;

    @Autowired
    private CatalogosMapper mapper;

    @GetMapping
    public ResponseEntity<List<CatTipoVinculoDto.Response>> getAll() {
        return ResponseEntity.ok(service.findAll().stream().map(mapper::toResponse).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatTipoVinculoDto.Response> getById(@PathVariable Integer id) {
        return service.findById(id)
                .map(entity -> ResponseEntity.ok(mapper.toResponse(entity)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CatTipoVinculoDto.Response> create(@Valid @RequestBody CatTipoVinculoDto.Request request) {
        CatTipoVinculo entity = mapper.toEntity(request);
        return ResponseEntity.ok(mapper.toResponse(service.save(entity)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CatTipoVinculoDto.Response> update(@PathVariable Integer id, @Valid @RequestBody CatTipoVinculoDto.Request request) {
        return service.findById(id)
                .map(existing -> {
                    CatTipoVinculo entity = mapper.toEntity(request);
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
