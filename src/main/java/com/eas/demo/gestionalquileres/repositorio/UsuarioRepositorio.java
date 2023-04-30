package com.eas.demo.gestionalquileres.repositorio;

import com.eas.demo.gestionalquileres.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
}
