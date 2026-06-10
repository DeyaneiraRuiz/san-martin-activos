package com.sgsi.riesgos.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "amenaza")
public class Amenaza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 150)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(length = 100)
    private String tipo;
}
