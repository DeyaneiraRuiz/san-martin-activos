package com.cooperativasanmartinrl.activos.service;

import com.cooperativasanmartinrl.activos.entity.Proceso;
import java.util.List;

public interface ProcesoService {

    List<Proceso> listar();

    Proceso obtener(Long id);

    Proceso guardar(Proceso proceso);

    Proceso actualizar(Long id, Proceso proceso);

    void eliminar(Long id);
}