package com.sgsi.security.service;

import com.sgsi.incidentes.entity.Incidente;
import com.sgsi.incidentes.repository.IncidenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service("incidenteSecurity")
@RequiredArgsConstructor
public class IncidenteSecurity {

    private final IncidenteRepository incidenteRepository;

    public boolean canRead(Authentication authentication, Integer incidenteId) {
        if (authentication == null || authentication.getName() == null) {
            return false;
        }

        String username = authentication.getName();
        Incidente incidente = incidenteRepository.findById(incidenteId).orElse(null);
        
        if (incidente == null) {
            return false; // Si no existe, dejamos que el controller tire 404, pero aquí denegamos acceso extra
        }

        // Es el reportero
        if (incidente.getReportador() != null && username.equals(incidente.getReportador().getUsername())) {
            return true;
        }

        // Es el técnico asignado
        if (incidente.getAsignadoA() != null && username.equals(incidente.getAsignadoA().getUsername())) {
            return true;
        }

        // Es un observador
        if (incidente.getObservadores() != null) {
            boolean isObserver = incidente.getObservadores().stream()
                    .anyMatch(obs -> username.equals(obs.getUsername()));
            if (isObserver) {
                return true;
            }
        }

        return false;
    }
}
