package com.eas.demo.gestionalquileres.controladores;

import com.eas.demo.gestionalquileres.modelos.Vehiculo;
import com.eas.demo.gestionalquileres.repositorio.VehiculoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class VehiculoControlador {

    @Autowired
    private VehiculoRepositorio repositorio;

    // Obtiene el listado de todos los vehiculos de la BBDD
    @GetMapping("/vehiculos")
    public List<Vehiculo> consultarVehiculos() {
        return repositorio.findAll();
    }
}
