package com.sgsi.incidentes.service;

import org.springframework.lang.NonNull;

import com.sgsi.incidentes.entity.IncidenteHistorial;
import com.sgsi.incidentes.repository.IncidenteHistorialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IncidenteHistorialService {

    private final IncidenteHistorialRepository repository;

    public List<IncidenteHistorial> findAll() { return repository.findAll(); }
    public Optional<IncidenteHistorial> findById(@NonNull Integer id) { return repository.findById(id); }
    public IncidenteHistorial save(@NonNull IncidenteHistorial entity) { return repository.save(entity); }
    public void deleteById(@NonNull Integer id) { repository.deleteById(id); }
}
