package com.sgsi.security.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, unique = true, nullable = false)
    private String username;

    @Column(name = "password_hash", length = 255, nullable = false)
    private String passwordHash;

    @Column(length = 150, unique = true)
    private String email;

    @Column(name = "nombre_completo", length = 200)
    private String nombreCompleto;

    @Column(length = 20)
    private String ci;

    @Column(name = "complemento_ci", length = 5)
    private String complementoCi;

    @Column(name = "apellido_paterno", length = 80)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", length = 80)
    private String apellidoMaterno;

    @Column(length = 80)
    private String nombres;

    @Column(length = 20)
    private String celular;

    @Column(length = 120)
    private String cargo;

    @Column(length = 60)
    private String departamento;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "usuario_rol",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Rol> roles = new HashSet<>();

    private Boolean activo;

    @Column(name = "ultimo_login")
    private LocalDateTime ultimoLogin;

    @Column(name = "password_changed_at")
    private LocalDateTime passwordChangedAt;

    @Column(name = "intentos_fallidos")
    private Integer intentosFallidos;

    @Column(name = "bloqueado_hasta")
    private LocalDateTime bloqueadoHasta;

    @Column(name = "twofa_enabled")
    private Boolean twofaEnabled;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "updated_by")
    private Integer updatedBy;
}