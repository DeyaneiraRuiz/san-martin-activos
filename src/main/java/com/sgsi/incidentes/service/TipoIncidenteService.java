package com.sgsi.incidentes.service;

import com.sgsi.incidentes.entity.CategoriaIncidente;
import com.sgsi.incidentes.entity.TipoIncidente;
import com.sgsi.incidentes.repository.CategoriaIncidenteRepository;
import com.sgsi.incidentes.repository.TipoIncidenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TipoIncidenteService {

    private final TipoIncidenteRepository repository;
    private final CategoriaIncidenteRepository categoriaRepository;

    public List<TipoIncidente> findAll() {
        return repository.findAll();
    }

    public Optional<TipoIncidente> findById(Integer id) {
        return repository.findById(id);
    }

    public TipoIncidente crear(TipoIncidente entity) {

        if (entity.getCodigo() == null || entity.getCodigo().isBlank()) {
            entity.setCodigo(generarCodigo());
        }

        if (entity.getEstado() == null || entity.getEstado().isBlank()) {
            entity.setEstado("activo");
        }

        return repository.save(entity);
    }

    public TipoIncidente actualizar(TipoIncidente entity) {
        return repository.save(entity);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    private String generarCodigo() {

        long correlativo = repository.count() + 1;

        return String.format(
                "TIP-INC-%03d",
                correlativo
        );
    }

    public Optional<CategoriaIncidente> getCategoriaById(Integer categoriaId) {
    if (categoriaId == null) return Optional.empty();
    return categoriaRepository.findById(categoriaId);
}
}