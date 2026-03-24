package com.cooperativasanmartinrl.activos.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "riesgo")
public class Riesgo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;
    private String tipoTratamiento;

    @ManyToOne
    @JoinColumn(name = "activo_id")
    private Activo activo;

    @ManyToOne
    @JoinColumn(name = "proceso_id")
    private Proceso proceso;

    @ManyToOne
    @JoinColumn(name = "amenaza_id")
    private Amenaza amenaza;

    @ManyToOne
    @JoinColumn(name = "vulnerabilidad_id")
    private Vulnerabilidad vulnerabilidad;
}