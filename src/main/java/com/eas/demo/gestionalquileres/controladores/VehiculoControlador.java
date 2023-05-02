package com.eas.demo.gestionalquileres.controladores;

import com.eas.demo.gestionalquileres.excepciones.ResourceNotFoundException;
import com.eas.demo.gestionalquileres.modelos.Vehiculo;
import com.eas.demo.gestionalquileres.repositorio.VehiculoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "http://localhost:4200/")
//@CrossOrigin(origins = "https://estefania0701.github.io/")
public class VehiculoControlador {

    @Autowired
    private VehiculoRepositorio repositorio;

    // Obtiene el listado de todos los vehiculos de la BBDD
    @GetMapping("/vehiculos")
    public List<Vehiculo> consultarVehiculos() {
        return repositorio.findAll();
    }

    // Obtiene un vehículo por su id
    @GetMapping("/vehiculos/{id}")
    public ResponseEntity<Vehiculo> consultarVehiculoPorId(@PathVariable Long id) {
        Vehiculo vehiculo = repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el vehículo con el ID: " + id));
        return ResponseEntity.ok(vehiculo);
    }

    // Actualiza el estado del vehículo (rentado o disponible)
    @PutMapping("/vehiculos/{id}")
    public ResponseEntity<Vehiculo> actualizarEstadoVehiculo(@PathVariable Long id, @RequestBody Vehiculo detallesVehiculo) {
        Vehiculo vehiculo = repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el vehículo con el ID: " + id));

        vehiculo.setEstado((detallesVehiculo.getEstado()));

        Vehiculo vehiculoActuwalizado = repositorio.save(vehiculo);

        return ResponseEntity.ok(vehiculoActuwalizado);
    }
}
