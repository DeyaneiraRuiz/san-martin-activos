package com.sgsi.incidentes.entity;

import com.sgsi.catalogos.entity.CatNivel;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "incidente_evaluacion_tecnica")
public class IncidenteEvaluacionTecnica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seguimiento_id", unique = true)
    private IncidenteSeguimiento seguimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "impacto_real_id")
    private CatNivel impactoReal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prioridad_id")
    private CatNivel prioridad;

    @Column(columnDefinition = "TEXT")
    private String causaRaiz;

    @Column(columnDefinition = "TEXT")
    private String solucionPropuesta;
}