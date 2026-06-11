package com.sgsi.incidentes.service;

import com.sgsi.incidentes.entity.CategoriaIncidente;
import com.sgsi.incidentes.repository.CategoriaIncidenteRepository;
import com.sgsi.security.entity.Usuario;
import com.sgsi.security.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaIncidenteService {

    private final CategoriaIncidenteRepository repository;
    private final UsuarioRepository usuarioRepository;

    public List<CategoriaIncidente> findAll() {
        return repository.findAll();
    }

    public Optional<CategoriaIncidente> findById(Integer id) {
        return repository.findById(id);
    }

    public CategoriaIncidente crear(CategoriaIncidente entity) {
        if (entity.getCodigo() == null || entity.getCodigo().isBlank()) {
            long correlativo = repository.count() + 1;
            entity.setCodigo(String.format("CAT-INC-%03d", correlativo));
        }

        if (entity.getEstado() == null || entity.getEstado().isBlank()) {
            entity.setEstado("activo");
        }

        return repository.save(entity);
    }

    public CategoriaIncidente actualizar(CategoriaIncidente entity) {
        return repository.save(entity);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    // ==================== MÉTODO AGREGADO ====================
    public Optional<Usuario> getUsuarioById(Integer usuarioId) {
        if (usuarioId == null) {
            return Optional.empty();
        }
        return usuarioRepository.findById(usuarioId);
    }
}