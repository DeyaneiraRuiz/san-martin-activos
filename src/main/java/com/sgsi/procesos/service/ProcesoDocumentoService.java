package com.sgsi.procesos.service;

import org.springframework.lang.NonNull;

import com.sgsi.procesos.entity.ProcesoDocumento;
import com.sgsi.procesos.repository.ProcesoDocumentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProcesoDocumentoService {

    private final ProcesoDocumentoRepository repository;

    public List<ProcesoDocumento> findAll() { return repository.findAll(); }
    public Optional<ProcesoDocumento> findById(@NonNull Integer id) { return repository.findById(id); }
    public ProcesoDocumento save(@NonNull ProcesoDocumento entity) { return repository.save(entity); }
    public void deleteById(@NonNull Integer id) { repository.deleteById(id); }
}
