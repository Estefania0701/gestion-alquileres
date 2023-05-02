package com.eas.demo.gestionalquileres.controladores;

import com.eas.demo.gestionalquileres.excepciones.ResourceNotFoundException;
import com.eas.demo.gestionalquileres.modelos.TarjetaUsuario;
import com.eas.demo.gestionalquileres.repositorio.TarjetaUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "http://localhost:4200/")
//@CrossOrigin(origins = "https://estefania0701.github.io/")
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

    // Obtiene una tarjeta por su id
    @GetMapping("/tarjetas-usuarios/{id}")
    public ResponseEntity<TarjetaUsuario> consultarTarjetaPorId (@PathVariable Long id) {
        TarjetaUsuario tarjetaUsuario = repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la tarjeta con el ID: " + id));
        return ResponseEntity.ok(tarjetaUsuario);
    }

        // Edita una tarjeta de usuario
    @PutMapping("/tarjetas-usuarios/{id}")
    public ResponseEntity<TarjetaUsuario> editarTarjeta (@PathVariable Long id, @RequestBody TarjetaUsuario detallesTarjeta) {
        TarjetaUsuario tarjetaUsuario = repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la tarjeta con el ID: " + id));

        tarjetaUsuario.setMediopago(detallesTarjeta.getMediopago());
        tarjetaUsuario.setNumerotarjeta(detallesTarjeta.getNumerotarjeta());

        TarjetaUsuario tarjetaUsuarioActualizada = repositorio.save(tarjetaUsuario);

        return ResponseEntity.ok(tarjetaUsuarioActualizada);
    }

    // Elimina una tarjeta de usuario
    @DeleteMapping("/tarjetas-usuarios/{id}")
    public ResponseEntity<Map<String, Boolean>> editarTarjeta2 (@PathVariable Long id) {
        TarjetaUsuario tarjetaUsuario = repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la tarjeta con el ID: " + id));

        repositorio.delete(tarjetaUsuario);

        Map<String, Boolean> respuesta = new HashMap<>();

        respuesta.put("eliminar", Boolean.TRUE);

        return ResponseEntity.ok(respuesta);
    }
}
