package com.sgsi.incidentes.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sla")
public class Sla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String nombre;

    @Column(name = "tiempo_respuesta")
    private Integer tiempoRespuesta;

    @Column(name = "tiempo_resolucion")
    private Integer tiempoResolucion;
}
