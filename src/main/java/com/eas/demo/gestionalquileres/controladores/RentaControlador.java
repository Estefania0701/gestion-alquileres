package com.eas.demo.gestionalquileres.controladores;

import com.eas.demo.gestionalquileres.excepciones.ResourceNotFoundException;
import com.eas.demo.gestionalquileres.modelos.Renta;
import com.eas.demo.gestionalquileres.repositorio.RentaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200/")
//@CrossOrigin(origins = "https://estefania0701.github.io/")
public class RentaControlador {

    @Autowired
    private RentaRepositorio repositorio;

    // Obtiene el listado de todas las rentas de la BBDD
    @GetMapping("/rentas")
    public List<Renta> consultarRentas() {
        return repositorio.findAll();
    }

    // Crea una nueva renta en la BBDD
    @PostMapping("/rentas")
    public Renta crearRenta(@RequestBody Renta renta) {
        return repositorio.save(renta);
    }

    // Obtiene una renta por su ID
    @GetMapping("/rentas/{id}")
    public ResponseEntity<Renta> consultarRentaPorId(@PathVariable Long id) {
        Renta renta = repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la renta con el ID: " + id));
        return ResponseEntity.ok(renta);
    }

    // Actualiza una renta
    @PutMapping("/rentas/{id}")
    public ResponseEntity<Renta> actualizarRenta (@PathVariable Long id, @RequestBody Renta detallesRenta) {
        Renta renta = repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la renta con el ID: " + id));

        renta.setEstado(detallesRenta.getEstado());
        renta.setInicio(detallesRenta.getInicio());
        renta.setFin(detallesRenta.getFin());

        Renta rentaActualizada = repositorio.save(renta);

        return ResponseEntity.ok(rentaActualizada);
    }
}
