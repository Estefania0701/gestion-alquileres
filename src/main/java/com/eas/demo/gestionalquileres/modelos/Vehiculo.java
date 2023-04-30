package com.eas.demo.gestionalquileres.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "vehiculos")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehiculo;

    @Column (name = "nombre", length = 60, nullable = false)
    private String nombre;

    @Column (name = "estado", length = 60, nullable = false)
    private String estado;

    @Column (name = "valor", length = 20, nullable = false)
    private Long valor;
}
