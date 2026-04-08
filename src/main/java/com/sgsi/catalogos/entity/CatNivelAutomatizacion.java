package com.sgsi.catalogos.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cat_nivel_automatizacion")
public class CatNivelAutomatizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, unique = true)
    private String nombre;
}
