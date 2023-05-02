package com.eas.demo.gestionalquileres.controladores;

import com.eas.demo.gestionalquileres.excepciones.ResourceNotFoundException;
import com.eas.demo.gestionalquileres.modelos.MedioPago;
import com.eas.demo.gestionalquileres.modelos.Usuario;
import com.eas.demo.gestionalquileres.repositorio.MedioPagoRepositorio;
import com.eas.demo.gestionalquileres.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "http://localhost:4200/")
//@CrossOrigin(origins = "https://estefania0701.github.io/")
public class MedioPagoControlador {

    @Autowired
    private MedioPagoRepositorio repositorio;

    @Autowired
    private UsuarioRepositorio repositorioU;

    // de todos los usuarios
    @GetMapping("/mediospago")
    public List<MedioPago> consultarMediosPago() {
        return repositorio.findAll();
    }

    // del ususario especificado
    @GetMapping("/mediospago/{idusuario}")
    public List<MedioPago> consultarMediosDePagoPorIdUsuario (@PathVariable Long idusuario) {

        List<MedioPago> mediosPago = repositorio.findAll();

        // filtro los medios de pago por el Id del usuario
        List<MedioPago> mediosPagoUsuario = mediosPago.stream()
                .filter(mp -> mp.getUsuario().getIdUsuario().equals(idusuario))
                .collect(Collectors.toList());

        return mediosPagoUsuario;
    }

    @PostMapping("mediospago/{idusuario}")
    public MedioPago agregarMedioPago(@PathVariable Long idusuario, @RequestBody MedioPago medioPagoDetalles) {

        // si se solicita con id de usuario inexistente
        Usuario usuario = repositorioU.findById(idusuario)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el usuario con el ID : " + idusuario));


        MedioPago medioPagoActualizado = new MedioPago();

        medioPagoActualizado.setUsuario(usuario);
        medioPagoActualizado.setTipo(medioPagoDetalles.getTipo());

        medioPagoActualizado.setTipo(medioPagoDetalles.getTipo());

        // si se quiere agregar una tarjeta
        if (medioPagoDetalles.getTipo().equals("Tarjeta crédito") || medioPagoDetalles.getTipo().equals("Tarjeta débito")) {
            medioPagoActualizado.setNumerotarjeta((medioPagoDetalles.getNumerotarjeta()));
        // si se quiere agregar efectivo
        } else {
            medioPagoActualizado.setNumerotarjeta(0L);
        }

        return repositorio.save(medioPagoActualizado);
    }


    // actualiza un medio de pago por su id
    @PutMapping("mediospago/{id}")
    public ResponseEntity<MedioPago> actualizarMedioPago(@PathVariable Long id, @RequestBody MedioPago medioPagoDetalles) {

        MedioPago medioPago = repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe un medio de pago con el ID: " + id));

        medioPago.setTipo(medioPagoDetalles.getTipo());

        // si se quiere actualizar a efectivo
        if (medioPagoDetalles.getTipo().equals("Efectivo")) {
            medioPago.setNumerotarjeta(0L);
        // si se quiere actualizar a una tarjeta
        } else if (medioPagoDetalles.getTipo().equals("Tarjeta crédito") || medioPagoDetalles.getTipo().equals("Tarjeta débito")){
            medioPago.setNumerotarjeta((medioPagoDetalles.getNumerotarjeta()));
        }

        MedioPago medioPagoActualizado = repositorio.save(medioPago);

        return ResponseEntity.ok(medioPagoActualizado);
    }

    // elimina un medio de pago por su id
    @DeleteMapping("mediospago/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarMedioPago (@PathVariable Long id) {

        MedioPago medioPago = repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el medio de pago con el id " + id));

        repositorio.delete(medioPago);

        Map<String, Boolean> respuesta = new HashMap<>();

        respuesta.put("eliminar", Boolean.TRUE);

        return ResponseEntity.ok(respuesta);
    }


}
