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
    private int idUsuario;
    private String username;
    private String nombre;
    private String apellido;
    private String password;
    private String fechaRegistro;

}
