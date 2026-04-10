package com.sgsi.catalogos.service;

import org.springframework.lang.NonNull;

import com.sgsi.catalogos.entity.CatNivel;
import com.sgsi.catalogos.repository.CatNivelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatNivelService {

    private final CatNivelRepository repository;

    public List<CatNivel> findAll() { return repository.findAll(); }
    public Optional<CatNivel> findById(@NonNull Integer id) { return repository.findById(id); }
    public CatNivel save(@NonNull CatNivel entity) { return repository.save(entity); }
    public void deleteById(@NonNull Integer id) { repository.deleteById(id); }
}
