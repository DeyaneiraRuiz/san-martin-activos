package com.sgsi.procesos.service;

import org.springframework.lang.NonNull;

import com.sgsi.procesos.entity.Documento;
import com.sgsi.procesos.repository.DocumentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DocumentoService {

    private final DocumentoRepository repository;

    public List<Documento> findAll() { return repository.findAll(); }
    public Optional<Documento> findById(@NonNull Integer id) { return repository.findById(id); }
    public Documento save(@NonNull Documento entity) { return repository.save(entity); }
    public void deleteById(@NonNull Integer id) { repository.deleteById(id); }
}
