package com.cooperativasanmartinrl.activos.service;

import com.cooperativasanmartinrl.activos.entity.Propietario;
import java.util.List;

public interface PropietarioService {

    List<Propietario> listar();

    Propietario obtener(Long id);

    Propietario guardar(Propietario propietario);

    Propietario actualizar(Long id, Propietario propietario);

    void eliminar(Long id);
}