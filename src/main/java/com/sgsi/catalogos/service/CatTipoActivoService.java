package com.sgsi.catalogos.service;

import org.springframework.lang.NonNull;

import com.sgsi.catalogos.entity.CatTipoActivo;
import com.sgsi.catalogos.repository.CatTipoActivoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatTipoActivoService {

    private final CatTipoActivoRepository repository;

    public List<CatTipoActivo> findAll() { return repository.findAll(); }
    public Optional<CatTipoActivo> findById(@NonNull Integer id) { return repository.findById(id); }
    public CatTipoActivo save(@NonNull CatTipoActivo entity) { return repository.save(entity); }
    public void deleteById(@NonNull Integer id) { repository.deleteById(id); }
}
