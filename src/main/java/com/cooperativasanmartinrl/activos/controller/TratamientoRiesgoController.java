package com.cooperativasanmartinrl.activos.controller;

import com.cooperativasanmartinrl.activos.entity.TratamientoRiesgo;
import com.cooperativasanmartinrl.activos.service.TratamientoRiesgoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tratamientos-riesgo")
@RequiredArgsConstructor
public class TratamientoRiesgoController {

    private final TratamientoRiesgoService service;

    @GetMapping
    public ResponseEntity<List<TratamientoRiesgo>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TratamientoRiesgo> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PostMapping
    public ResponseEntity<TratamientoRiesgo> crear(@RequestBody TratamientoRiesgo t) {
        return ResponseEntity.ok(service.guardar(t));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TratamientoRiesgo> actualizar(@PathVariable Long id, @RequestBody TratamientoRiesgo t) {
        return ResponseEntity.ok(service.actualizar(id, t));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}