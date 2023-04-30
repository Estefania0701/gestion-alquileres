package com.eas.demo.gestionalquileres.controladores;

import com.eas.demo.gestionalquileres.modelos.Renta;
import com.eas.demo.gestionalquileres.repositorio.RentaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
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


}
