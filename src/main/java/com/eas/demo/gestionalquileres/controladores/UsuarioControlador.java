package com.eas.demo.gestionalquileres.controladores;

import com.eas.demo.gestionalquileres.excepciones.ResourceNotFoundException;
import com.eas.demo.gestionalquileres.modelos.MedioPago;
import com.eas.demo.gestionalquileres.modelos.Usuario;
import com.eas.demo.gestionalquileres.repositorio.MedioPagoRepositorio;
import com.eas.demo.gestionalquileres.repositorio.UsuarioRepositorio;
import com.eas.demo.gestionalquileres.utils.FechaActual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
//@CrossOrigin(origins = "http://localhost:4200/")
@CrossOrigin(origins = "https://estefania0701.github.io/")
public class UsuarioControlador {

    @Autowired
    private UsuarioRepositorio repositorio;

    @Autowired
    private MedioPagoRepositorio repositorioMP;


    // Obtiene el listado de todos los usuarios de la BBDD
    @GetMapping("/usuarios")
    public List<Usuario> consultarUsuarios() {
        return repositorio.findAll();
    }

    // Crea un nuevo usuario en la BBDD
    @PostMapping("/usuarios")
    public Usuario crearUsuario(@RequestBody Usuario usuario) {

        FechaActual fechaActual = new FechaActual();

        String fechaRegistro = fechaActual.obtenerFechaActual();
        usuario.setRegistro(fechaRegistro);
        return repositorio.save(usuario);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<List<Object>> consultarUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el usuario con el ID : " + id));

        List<MedioPago> mediosPago = repositorioMP.findAll();

        // filtro los medios de pago por el Id del usuario
        List<MedioPago> mediosPagoUsuario = mediosPago.stream()
                .filter(mp -> mp.getUsuario().getIdUsuario().equals(usuario.getIdUsuario()))
                .collect(Collectors.toList());


        List<Object> respuesta = new ArrayList<>();

        respuesta.add(usuario);
        respuesta.add(mediosPagoUsuario);

        return ResponseEntity.ok(respuesta);
    }

    // Elimina un usuario de la BBDD
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

    @PutMapping("/usuarios/{id}")
    public String eliminarUsuario() {
        return "No es posible actualizar usuarios";
    }

    @PutMapping("/usuarios")
    public String eliminaUsuarios() {
        return "No es posible actualizar usuarios";
    }
}
