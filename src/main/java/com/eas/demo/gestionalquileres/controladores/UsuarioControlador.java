package com.eas.demo.gestionalquileres.controladores;

import com.eas.demo.gestionalquileres.modelos.Usuario;
import com.eas.demo.gestionalquileres.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class UsuarioControlador {

    @Autowired
    private UsuarioRepositorio repositorio;

    @GetMapping("/usuarios")
    public List<Usuario> listarTodosLosUsuarios() {
        return repositorio.findAll();
    }
}
