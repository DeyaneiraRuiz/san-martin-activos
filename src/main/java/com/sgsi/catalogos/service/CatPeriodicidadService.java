package com.sgsi.catalogos.service;

import org.springframework.lang.NonNull;

import com.sgsi.catalogos.entity.CatPeriodicidad;
import com.sgsi.catalogos.repository.CatPeriodicidadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatPeriodicidadService {

    private final CatPeriodicidadRepository repository;

    public List<CatPeriodicidad> findAll() { return repository.findAll(); }
    public Optional<CatPeriodicidad> findById(@NonNull Integer id) { return repository.findById(id); }
    public CatPeriodicidad save(@NonNull CatPeriodicidad entity) { return repository.save(entity); }
    public void deleteById(@NonNull Integer id) { repository.deleteById(id); }
}
