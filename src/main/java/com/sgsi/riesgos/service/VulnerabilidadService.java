package com.sgsi.riesgos.service;

import org.springframework.lang.NonNull;

import com.sgsi.riesgos.entity.Vulnerabilidad;
import com.sgsi.riesgos.repository.VulnerabilidadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VulnerabilidadService {

    private final VulnerabilidadRepository repository;

    public List<Vulnerabilidad> findAll() { return repository.findAll(); }
    public Optional<Vulnerabilidad> findById(@NonNull Integer id) { return repository.findById(id); }
    public Vulnerabilidad save(@NonNull Vulnerabilidad entity) { return repository.save(entity); }
    public void deleteById(@NonNull Integer id) { repository.deleteById(id); }
}
