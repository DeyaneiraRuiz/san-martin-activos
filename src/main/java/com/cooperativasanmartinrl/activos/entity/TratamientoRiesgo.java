package com.cooperativasanmartinrl.activos.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tratamiento_riesgo")
public class TratamientoRiesgo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String estrategia;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "riesgo_id")
    private Riesgo riesgo;
}