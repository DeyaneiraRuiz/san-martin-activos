package com.cooperativasanmartinrl.activos.service;

import com.cooperativasanmartinrl.activos.entity.NivelRiesgo;
import java.util.List;

public interface NivelRiesgoService {

    List<NivelRiesgo> listar();

    NivelRiesgo obtener(Long id);

    NivelRiesgo guardar(NivelRiesgo nivelRiesgo);

    NivelRiesgo actualizar(Long id, NivelRiesgo nivelRiesgo);

    void eliminar(Long id);
}