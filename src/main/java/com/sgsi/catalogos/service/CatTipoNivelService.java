package com.sgsi.catalogos.service;

import org.springframework.lang.NonNull;

import com.sgsi.catalogos.entity.CatTipoNivel;
import com.sgsi.catalogos.repository.CatTipoNivelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatTipoNivelService {

    private final CatTipoNivelRepository repository;

    public List<CatTipoNivel> findAll() { return repository.findAll(); }
    public Optional<CatTipoNivel> findById(@NonNull Integer id) { return repository.findById(id); }
    public CatTipoNivel save(@NonNull CatTipoNivel entity) { return repository.save(entity); }
    public void deleteById(@NonNull Integer id) { repository.deleteById(id); }
}
