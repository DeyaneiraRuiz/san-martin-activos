package com.sgsi.incidentes.controller;

import com.sgsi.incidentes.dto.IncidenteDto;
import com.sgsi.incidentes.entity.Incidente;
import com.sgsi.incidentes.mapper.IncidentesMapper;
import com.sgsi.incidentes.service.IncidenteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidentes")
@RequiredArgsConstructor
public class IncidenteController {

    private final IncidenteService incidenteService;
    private final IncidentesMapper mapper;

    @PreAuthorize("""
        hasAnyRole(
            'ADMINISTRADOR',
            'TECNICO',
            'ANALISTA_SEGURIDAD',
            'USUARIO'
        )
    """)
    @GetMapping
    public ResponseEntity<List<IncidenteDto.Response>> findAll() {

        List<IncidenteDto.Response> response = incidenteService.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @PreAuthorize(
        "hasRole('ADMINISTRADOR') or @incidenteSecurity.canRead(authentication,#id)"
    )
    @GetMapping("/{id}")
    public ResponseEntity<IncidenteDto.Response> findById(
            @PathVariable Integer id) {

        return incidenteService.findById(id)
                .map(mapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','USUARIO')")
    @PostMapping
    public ResponseEntity<IncidenteDto.Response> create(
            @Valid @RequestBody IncidenteDto.Request request) {

        Incidente incidente = incidenteService.crear(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toResponse(incidente));
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR','TECNICO')")
    @PutMapping("/{id}")
    public ResponseEntity<IncidenteDto.Response> update(
            @PathVariable Integer id,
            @Valid @RequestBody IncidenteDto.Request request) {

        try {

            Incidente incidente = incidenteService.actualizar(id, request);

            return ResponseEntity.ok(
                    mapper.toResponse(incidente)
            );

        } catch (RuntimeException ex) {

            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer id) {

        if (incidenteService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        incidenteService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    // ============================================
    // NUEVOS ENDPOINTS PARA NOTIFICACIONES
    // ============================================

    /**
     * Asignar un técnico al incidente
     * Dispara notificación: Técnico asignado + Reportador + Observadores
     */
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PatchMapping("/{id}/asignar-tecnico")
    public ResponseEntity<IncidenteDto.Response> asignarTecnico(
            @PathVariable Integer id,
            @RequestParam Integer tecnicoId) {

        try {
            Incidente incidente = incidenteService.asignarTecnico(id, tecnicoId);
            return ResponseEntity.ok(mapper.toResponse(incidente));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Cambiar el estado del incidente
     * Dispara notificación según el nuevo estado:
     * - en_curso → Planificación
     * - resuelto → Resolución
     * - cerrado → Cierre
     */
    @PreAuthorize("hasAnyRole('ADMINISTRADOR','TECNICO')")
    @PatchMapping("/{id}/estado")
    public ResponseEntity<IncidenteDto.Response> cambiarEstado(
            @PathVariable Integer id,
            @RequestParam Integer estadoId) {

        try {
            Incidente incidente = incidenteService.cambiarEstado(id, estadoId);
            return ResponseEntity.ok(mapper.toResponse(incidente));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Rechazar la solución de un incidente (vuelve a estado ASIGNADO)
     * Dispara notificación: Técnico + Reportador + Observadores
     */
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PatchMapping("/{id}/rechazar")
    public ResponseEntity<IncidenteDto.Response> rechazarSolucion(
            @PathVariable Integer id,
            @RequestParam String motivo) {

        try {
            Incidente incidente = incidenteService.rechazarSolucion(id, motivo);
            return ResponseEntity.ok(mapper.toResponse(incidente));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}