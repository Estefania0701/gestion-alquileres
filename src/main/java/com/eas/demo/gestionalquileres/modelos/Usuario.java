package com.eas.demo.gestionalquileres.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column (name = "username", length = 60, nullable = false)
    private String username;

    @Column (name = "nombre", length = 60, nullable = false)
    private String nombre;

    @Column (name = "apellido", length = 60, nullable = false)
    private String apellido;

    @Column (name = "password", length = 60, nullable = false)
    private String password;

    @Column (name = "registro", nullable = false)
    private String registro;

}
