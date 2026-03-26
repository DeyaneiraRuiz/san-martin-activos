package com.cooperativasanmartinrl.activos.service;

import com.cooperativasanmartinrl.activos.entity.Rol;
import java.util.List;

public interface RolService {

    List<Rol> listar();

    Rol obtener(Long id);

    Rol guardar(Rol rol);

    Rol actualizar(Long id, Rol rol);

    void eliminar(Long id);
}