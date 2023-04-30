package com.eas.demo.gestionalquileres.repositorio;

import com.eas.demo.gestionalquileres.modelos.Renta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentaRepositorio extends JpaRepository<Renta, Long> {
}
