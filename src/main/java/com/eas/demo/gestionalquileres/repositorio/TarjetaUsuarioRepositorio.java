package com.eas.demo.gestionalquileres.repositorio;

import com.eas.demo.gestionalquileres.modelos.TarjetaUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarjetaUsuarioRepositorio extends JpaRepository<TarjetaUsuario, Long> {
}
