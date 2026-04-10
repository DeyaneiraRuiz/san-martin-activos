package com.sgsi.incidentes.service;

import com.sgsi.incidentes.entity.IncidenteEvidencia;
import com.sgsi.incidentes.repository.IncidenteEvidenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IncidenteEvidenciaService {

    private final IncidenteEvidenciaRepository repository;

    public List<IncidenteEvidencia> findAll() { return repository.findAll(); }
    public Optional<IncidenteEvidencia> findById(@NonNull Integer id) { return repository.findById(id); }
    public IncidenteEvidencia save(@NonNull IncidenteEvidencia entity) { return repository.save(entity); }
    public void deleteById(@NonNull Integer id) { repository.deleteById(id); }
}
