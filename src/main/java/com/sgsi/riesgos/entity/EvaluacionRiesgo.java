package com.sgsi.riesgos.entity;

import com.sgsi.security.entity.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "evaluacion_riesgo")
public class EvaluacionRiesgo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "riesgo_id")
    private Riesgo riesgo;

    private Integer impacto;
    private Integer probabilidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nivel_riesgo_id")
    private NivelRiesgo nivelRiesgo;

    private LocalDateTime fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evaluado_por")
    private Usuario evaluadoPor;
}
