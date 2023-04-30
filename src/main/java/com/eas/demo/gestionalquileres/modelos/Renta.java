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

    // Llaves foraneas
    @ManyToOne // muchas rentas para un usuario, un usuario para una renta
    @JoinColumn (name = "usuario_id")
    private Usuario usuario;

    @ManyToOne // una renta tiene un medio de pago, un medio de pago puede estar en muchas rentas
    @JoinColumn (name = "mediopago_id")
    private MedioPago mediopago;

    @ManyToOne // una renta tiene solo un vehiculo, un vehiculo solo puede estar en una renta
    @JoinColumn (name = "vehiculo_id")
    private Vehiculo vehiculo;

    @Column (name = "estado", length = 60, nullable = true)
    private String estado;

    @Column (name = "inicio", length = 60, nullable = true)
    private String inicio;

    @Column (name = "fin", length = 60, nullable = true)
    private String fin;
}
