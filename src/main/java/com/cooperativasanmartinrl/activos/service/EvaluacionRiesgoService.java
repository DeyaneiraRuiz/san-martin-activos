package com.cooperativasanmartinrl.activos.service;

import com.cooperativasanmartinrl.activos.entity.EvaluacionRiesgo;
import java.util.List;

public interface EvaluacionRiesgoService {

    List<EvaluacionRiesgo> listar();

    EvaluacionRiesgo obtener(Long id);

    EvaluacionRiesgo guardar(EvaluacionRiesgo evaluacion);

    EvaluacionRiesgo actualizar(Long id, EvaluacionRiesgo evaluacion);

    void eliminar(Long id);
}