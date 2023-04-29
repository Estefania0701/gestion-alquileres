package com.eas.demo.gestionalquileres.controladores;

import com.eas.demo.gestionalquileres.modelos.Usuario;
import com.eas.demo.gestionalquileres.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioControlador {

    @Autowired
    private UsuarioRepositorio repositorio;

    // Obtiene el listado de todos los usuarios de la BBDD
    @GetMapping("/usuarios")
    public List<Usuario> consultarUsuarios() {
        return repositorio.findAll();
    }

    // Crea un nuevo usuario en la BBDD
    @PostMapping("/usuarios")
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return repositorio.save(usuario);
    }
}
