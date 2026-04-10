package com.sgsi.catalogos.service;

import org.springframework.lang.NonNull;

import com.sgsi.catalogos.entity.CatEstadoActivo;
import com.sgsi.catalogos.repository.CatEstadoActivoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatEstadoActivoService {

    private final CatEstadoActivoRepository repository;

    public List<CatEstadoActivo> findAll() { return repository.findAll(); }
    public Optional<CatEstadoActivo> findById(@NonNull Integer id) { return repository.findById(id); }
    public CatEstadoActivo save(@NonNull CatEstadoActivo entity) { return repository.save(entity); }
    public void deleteById(@NonNull Integer id) { repository.deleteById(id); }
}
