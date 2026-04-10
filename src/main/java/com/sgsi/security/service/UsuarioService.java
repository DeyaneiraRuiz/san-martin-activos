package com.sgsi.security.service;

import org.springframework.lang.NonNull;

import com.sgsi.security.dto.UsuarioDto;
import com.sgsi.security.entity.Usuario;
import com.sgsi.security.mapper.SecurityMapper;
import com.sgsi.security.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@SuppressWarnings("null")
public class UsuarioService {

    private final UsuarioRepository repository;
    private final SecurityMapper mapper;

    public List<UsuarioDto.Response> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    public UsuarioDto.Response findById(@NonNull Integer id) {
        Usuario entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));
        return mapper.toResponse(entity);
    }

    public UsuarioDto.Response save(UsuarioDto.Request request) {
        Usuario entity = mapper.toEntity(request);
        // FIXME: You might want to encode password here if this is used for registration. 
        // We'll address this when integrating JWT.
        Usuario saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    public UsuarioDto.Response update(Integer id, UsuarioDto.Request request) {
        Usuario existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));
        
        existing.setUsername(request.username());
        if (request.password() != null && !request.password().isBlank()) {
            existing.setPasswordHash(request.password()); // Remember to hash later
        }
        existing.setEmail(request.email());
        existing.setActivo(request.activo());
        // For rol, we could set a Rol entity instead, but let's just use the setter if available or mapstruct update
        // We'll try manual setter. Assuming Usuario entity has setRol or setRolId.
        
        Usuario saved = repository.save(existing);
        return mapper.toResponse(saved);
    }

    public void deleteById(@NonNull Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Usuario no encontrado con ID: " + id);
        }
        repository.deleteById(id);
    }
}
