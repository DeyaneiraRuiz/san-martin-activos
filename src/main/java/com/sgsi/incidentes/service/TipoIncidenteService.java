package com.sgsi.incidentes.service;

import com.sgsi.incidentes.entity.TipoIncidente;
import com.sgsi.incidentes.repository.TipoIncidenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TipoIncidenteService {

    private final TipoIncidenteRepository repository;

    public List<TipoIncidente> findAll() {
        return repository.findAll();
    }

    public Optional<TipoIncidente> findById(@NonNull Integer id) {
        return repository.findById(id);
    }

    public TipoIncidente save(@NonNull TipoIncidente entity) {
        if (entity.getCodigo() == null || entity.getCodigo().isBlank()) {
            long count = repository.count();
            entity.setCodigo(String.format("T-INC-%03d", count + 1));
        }
        return repository.save(entity);
    }

    public void deleteById(@NonNull Integer id) {
        repository.deleteById(id);
    }
}