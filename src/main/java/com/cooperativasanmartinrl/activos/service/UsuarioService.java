package com.cooperativasanmartinrl.activos.service;

import com.cooperativasanmartinrl.activos.entity.Usuario;
import java.util.List;

public interface UsuarioService {

    List<Usuario> listar();

    Usuario obtener(Long id);

    Usuario guardar(Usuario usuario);

    Usuario actualizar(Long id, Usuario usuario);

    void eliminar(Long id);
}