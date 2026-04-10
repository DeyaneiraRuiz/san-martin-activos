package com.sgsi.catalogos.service;

import org.springframework.lang.NonNull;

import com.sgsi.catalogos.entity.CatNivelAutomatizacion;
import com.sgsi.catalogos.repository.CatNivelAutomatizacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatNivelAutomatizacionService {

    private final CatNivelAutomatizacionRepository repository;

    public List<CatNivelAutomatizacion> findAll() { return repository.findAll(); }
    public Optional<CatNivelAutomatizacion> findById(@NonNull Integer id) { return repository.findById(id); }
    public CatNivelAutomatizacion save(@NonNull CatNivelAutomatizacion entity) { return repository.save(entity); }
    public void deleteById(@NonNull Integer id) { repository.deleteById(id); }
}
