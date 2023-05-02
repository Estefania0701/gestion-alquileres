package com.eas.demo.gestionalquileres.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name = "rentas")
public class Renta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRenta;

    @Column (name = "vehiculo", length = 20, nullable = false)
    private String vehiculo;

    @Column (name = "valor", length = 20, nullable = false)
    private Long valor;

    @ManyToOne
    @JoinColumn (name = "mediopago_id")
    private MedioPago medioPago;

    @Column (name = "estado", length = 60, nullable = false)
    private String estado;

    @Column (name = "inicio", length = 60, nullable = true)
    private String inicio;

    @Column (name = "fin", length = 60, nullable = true)
    private String fin;


}
