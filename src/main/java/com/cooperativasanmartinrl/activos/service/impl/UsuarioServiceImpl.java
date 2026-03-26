package com.cooperativasanmartinrl.activos.service.impl;

import com.cooperativasanmartinrl.activos.entity.Usuario;
import com.cooperativasanmartinrl.activos.repository.UsuarioRepository;
import com.cooperativasanmartinrl.activos.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repo;

    public List<Usuario> listar() { return repo.findAll(); }

    public Usuario obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public Usuario guardar(Usuario u) { return repo.save(u); }

    public Usuario actualizar(Long id, Usuario data) {
        Usuario u = obtener(id);
        u.setUsername(data.getUsername());
        u.setPassword(data.getPassword());
        u.setRol(data.getRol());
        return repo.save(u);
    }

    public void eliminar(Long id) { repo.deleteById(id); }
}