package com.eas.demo.gestionalquileres.repositorio;

import com.eas.demo.gestionalquileres.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
}
