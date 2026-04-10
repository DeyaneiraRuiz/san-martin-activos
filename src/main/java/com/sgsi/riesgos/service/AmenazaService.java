package com.sgsi.riesgos.service;

import org.springframework.lang.NonNull;

import com.sgsi.riesgos.entity.Amenaza;
import com.sgsi.riesgos.repository.AmenazaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AmenazaService {

    private final AmenazaRepository repository;

    public List<Amenaza> findAll() { return repository.findAll(); }
    public Optional<Amenaza> findById(@NonNull Integer id) { return repository.findById(id); }
    public Amenaza save(@NonNull Amenaza entity) { return repository.save(entity); }
    public void deleteById(@NonNull Integer id) { repository.deleteById(id); }
}
