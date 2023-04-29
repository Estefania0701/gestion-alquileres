package com.eas.demo.gestionalquileres.controladores;

import com.eas.demo.gestionalquileres.excepciones.ResourceNotFoundException;
import com.eas.demo.gestionalquileres.modelos.Usuario;
import com.eas.demo.gestionalquileres.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // Obtiene un usuario por su ID
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> consultarUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el usuario con el ID : " + id));
        return ResponseEntity.ok(usuario);
    }

    // Elimina un usuari de la BBDD
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarUsuario (@PathVariable Long id) {
        // si el usuario no existe, levanto la excepción personalizada
        Usuario usuario = repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el usuario con el ID : " + id));

        // ----- Si el usuario sí existe

        // elimino el usuario
        repositorio.delete(usuario);

        // HashMap con la respuesta de la eliminación del usuario
        Map<String, Boolean> respuesta = new HashMap<>();

        // ("eliminar", true)
        respuesta.put("eliminar", Boolean.TRUE);

        return ResponseEntity.ok(respuesta);
    }
}
