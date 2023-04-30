package com.eas.demo.gestionalquileres.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tarjetasusuarios")
public class TarjetaUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTarjeta;

    @ManyToOne
    @JoinColumn (name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn (name = "mediopago_id")
    private MedioPago mediopago;

    @Column (name = "numerotarjeta", length = 20, nullable = false)
    private Long numerotarjeta;


}
