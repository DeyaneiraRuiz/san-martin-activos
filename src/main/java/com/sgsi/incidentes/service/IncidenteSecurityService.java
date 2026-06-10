package com.sgsi.incidentes.service;

import com.sgsi.incidentes.entity.Incidente;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("incidenteSecurity")
@RequiredArgsConstructor
public class IncidenteSecurityService {

    private final IncidenteService incidenteService;

    /**
     * Permite leer un incidente si el usuario autenticado es quien lo reportó
     * o si está asignado a él.
     */
    public boolean canRead(Authentication authentication, Integer incidenteId) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }
        String username = authentication.getName();
        Optional<Incidente> incidente = incidenteService.findById(incidenteId);
        if (incidente.isEmpty()) {
            return false;
        }
        Incidente inc = incidente.get();
        boolean esReportador = inc.getReportadoPor() != null
                && username.equals(inc.getReportadoPor().getUsername());
        boolean esAsignado = inc.getAsignadoA() != null
                && username.equals(inc.getAsignadoA().getUsername());
        return esReportador || esAsignado;
    }
}
