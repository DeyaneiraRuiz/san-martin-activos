package com.sgsi.catalogos.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cat_estado_incidente")
public class CatEstadoIncidente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, unique = true)
    private String nombre;
}
