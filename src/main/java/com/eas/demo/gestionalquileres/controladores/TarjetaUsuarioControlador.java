package com.eas.demo.gestionalquileres.controladores;

import com.eas.demo.gestionalquileres.modelos.TarjetaUsuario;
import com.eas.demo.gestionalquileres.repositorio.TarjetaUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class TarjetaUsuarioControlador {

    @Autowired
    private TarjetaUsuarioRepositorio repositorio;

    // Obtiene las tarjetas de usuarios guardadas
    @GetMapping("/tarjetas-usuarios")
    public List<TarjetaUsuario> consultarTarjetas() {
        return repositorio.findAll();
    }

    // Guarda una nueva tarjeta de usuario
    @PostMapping("/tarjetas-usuarios")
    public TarjetaUsuario guardarTarjeta(@RequestBody TarjetaUsuario tarjetaUsuario) {
        return repositorio.save(tarjetaUsuario);
    }
}
