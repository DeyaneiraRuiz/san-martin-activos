package com.cooperativasanmartinrl.activos.service.impl;

import com.cooperativasanmartinrl.activos.entity.Documento;
import com.cooperativasanmartinrl.activos.repository.DocumentoRepository;
import com.cooperativasanmartinrl.activos.service.DocumentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentoServiceImpl implements DocumentoService {

    private final DocumentoRepository repo;

    @Override
    public List<Documento> listar() {
        return repo.findAll();
    }

    @Override
    public Documento obtener(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Documento no encontrado"));
    }

    @Override
    public Documento guardar(Documento d) {
        return repo.save(d);
    }

    @Override
    public Documento actualizar(Long id, Documento data) {
        Documento d = obtener(id);
        d.setNombre(data.getNombre());
        d.setUrl(data.getUrl());
        return repo.save(d);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}