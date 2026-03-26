package com.cooperativasanmartinrl.activos.service;

import com.cooperativasanmartinrl.activos.entity.Riesgo;
import java.util.List;

public interface RiesgoService {

    List<Riesgo> listar();

    Riesgo obtener(Long id);

    Riesgo guardar(Riesgo riesgo);

    Riesgo actualizar(Long id, Riesgo riesgo);

    void eliminar(Long id);
}