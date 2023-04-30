package com.eas.demo.gestionalquileres.repositorio;

import com.eas.demo.gestionalquileres.modelos.MedioPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedioPagoRepositorio extends JpaRepository<MedioPago, Long> {
}
