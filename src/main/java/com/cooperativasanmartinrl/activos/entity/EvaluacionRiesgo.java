package com.cooperativasanmartinrl.activos.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "evaluacion_riesgo")
public class EvaluacionRiesgo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int impacto;
    private int probabilidad;

    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "riesgo_id")
    private Riesgo riesgo;

    @ManyToOne
    @JoinColumn(name = "nivel_riesgo_id")
    private NivelRiesgo nivelRiesgo;

    @ManyToOne
    @JoinColumn(name = "evaluado_por")
    private Usuario evaluadoPor;
}