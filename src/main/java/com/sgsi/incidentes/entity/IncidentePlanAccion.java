package com.sgsi.incidentes.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "incidente_plan_accion")
public class IncidentePlanAccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seguimiento_id", unique = true)
    private IncidenteSeguimiento seguimiento;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private LocalDate fechaCompromiso;
}