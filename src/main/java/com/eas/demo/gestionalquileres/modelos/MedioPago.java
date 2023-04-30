package com.eas.demo.gestionalquileres.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table (name = "mediospago")
public class MedioPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMediopago;

    @Column (name = "tipo")
    private String tipo;
}
