package com.eas.demo.gestionalquileres.controladores;

import com.eas.demo.gestionalquileres.modelos.MedioPago;
import com.eas.demo.gestionalquileres.repositorio.MedioPagoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "http://localhost:4200/")
//@CrossOrigin(origins = "https://estefania0701.github.io/")
public class MedioPagoControlador {

    @Autowired
    private MedioPagoRepositorio repositorio;

    @GetMapping("/mediospago")
    public List<MedioPago> consultarMediosPago() {
        return repositorio.findAll();
    }
}
