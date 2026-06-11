package com.sgsi.incidentes.entity;

import com.sgsi.security.entity.Usuario;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "incidente_cierre")
public class IncidenteCierre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seguimiento_id", unique = true)
    private IncidenteSeguimiento seguimiento;

    @Column(columnDefinition = "TEXT")
    private String comentarioCierre;

    private LocalDate fechaCierre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cerrado_por_id")
    private Usuario cerradoPor;
}