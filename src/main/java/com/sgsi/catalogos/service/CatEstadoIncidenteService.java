package com.sgsi.catalogos.service;

import org.springframework.lang.NonNull;

import com.sgsi.catalogos.entity.CatEstadoIncidente;
import com.sgsi.catalogos.repository.CatEstadoIncidenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatEstadoIncidenteService {

    private final CatEstadoIncidenteRepository repository;

    public List<CatEstadoIncidente> findAll() { return repository.findAll(); }
    public Optional<CatEstadoIncidente> findById(@NonNull Integer id) { return repository.findById(id); }
    public CatEstadoIncidente save(@NonNull CatEstadoIncidente entity) { return repository.save(entity); }
    public void deleteById(@NonNull Integer id) { repository.deleteById(id); }
}
