package com.sgsi.catalogos.service;

import org.springframework.lang.NonNull;

import com.sgsi.catalogos.entity.CatTipoVinculo;
import com.sgsi.catalogos.repository.CatTipoVinculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatTipoVinculoService {

    private final CatTipoVinculoRepository repository;

    public List<CatTipoVinculo> findAll() { return repository.findAll(); }
    public Optional<CatTipoVinculo> findById(@NonNull Integer id) { return repository.findById(id); }
    public CatTipoVinculo save(@NonNull CatTipoVinculo entity) { return repository.save(entity); }
    public void deleteById(@NonNull Integer id) { repository.deleteById(id); }
}
