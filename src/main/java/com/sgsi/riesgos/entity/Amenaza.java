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
}
