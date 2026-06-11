package com.sgsi.incidentes.service;

import com.sgsi.catalogos.entity.CatEstadoIncidente;
import com.sgsi.catalogos.repository.CatEstadoIncidenteRepository;
import com.sgsi.incidentes.entity.Incidente;
import com.sgsi.incidentes.entity.IncidenteHistorial;
import com.sgsi.incidentes.repository.IncidenteHistorialRepository;
import com.sgsi.incidentes.repository.IncidenteRepository;
import com.sgsi.security.entity.Usuario;
import com.sgsi.security.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IncidenteHistorialService {

    private final IncidenteHistorialRepository repository;

    private final IncidenteRepository incidenteRepository;
    private final CatEstadoIncidenteRepository estadoRepository;
    private final UsuarioRepository usuarioRepository;

    public List<IncidenteHistorial> findAll() {
        return repository.findAll();
    }

    public Optional<IncidenteHistorial> findById(
            @NonNull Integer id) {

        return repository.findById(id);
    }

    public IncidenteHistorial crear(
            @NonNull IncidenteHistorial historial) {

        validarRelaciones(historial);

        if (historial.getFecha() == null) {
            historial.setFecha(LocalDateTime.now());
        }

        return repository.save(historial);
    }

    public IncidenteHistorial actualizar(
            @NonNull IncidenteHistorial historial) {

        validarRelaciones(historial);

        return repository.save(historial);
    }

    public void deleteById(
            @NonNull Integer id) {

        repository.deleteById(id);
    }

    private void validarRelaciones(
            IncidenteHistorial historial) {

        if (historial.getIncidente() != null
                && historial.getIncidente().getId() != null) {

            Incidente incidente = incidenteRepository.findById(
                    historial.getIncidente().getId())
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Incidente no encontrado"));

            historial.setIncidente(incidente);
        }

        if (historial.getEstado() != null
                && historial.getEstado().getId() != null) {

            CatEstadoIncidente estado = estadoRepository.findById(
                    historial.getEstado().getId())
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Estado no encontrado"));

            historial.setEstado(estado);
        }

        if (historial.getChangedBy() != null
                && historial.getChangedBy().getId() != null) {

            Usuario usuario = usuarioRepository.findById(
                    historial.getChangedBy().getId())
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Usuario no encontrado"));

            historial.setChangedBy(usuario);
        }
    }
}