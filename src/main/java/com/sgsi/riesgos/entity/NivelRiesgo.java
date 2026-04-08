package com.sgsi.riesgos.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "nivel_riesgo")
public class NivelRiesgo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String nombre;

    @Column(name = "valor_min")
    private Integer valorMin;

    @Column(name = "valor_max")
    private Integer valorMax;

    @Column(length = 20)
    private String color;
}
