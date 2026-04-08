package com.sgsi.catalogos.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cat_nivel")
public class CatNivel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String nombre;

    private Integer valor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_id")
    private CatTipoNivel tipoNivel;
}
