package com.eas.demo.gestionalquileres.controladores;

import com.eas.demo.gestionalquileres.excepciones.ResourceNotFoundException;
import com.eas.demo.gestionalquileres.modelos.MedioPago;
import com.eas.demo.gestionalquileres.modelos.Renta;
import com.eas.demo.gestionalquileres.repositorio.MedioPagoRepositorio;
import com.eas.demo.gestionalquileres.repositorio.RentaRepositorio;
import com.eas.demo.gestionalquileres.utils.FechaActual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200/")
//@CrossOrigin(origins = "https://estefania0701.github.io/")
public class RentaControlador {

    FechaActual fechaActual = new FechaActual();

    @Autowired
    private RentaRepositorio repositorio;

    @Autowired
    private MedioPagoRepositorio repositorioMP;

    // Obtiene el listado de todas las rentas de la BBDD
    @GetMapping("/rentas")
    public List<Renta> consultarRentas() {
        return repositorio.findAll();
    }

    // Crea una nueva renta en la BBDD
    @PostMapping("/rentas/{idmediopago}")
    public Renta crearRenta(@RequestBody Renta renta, @PathVariable Long idmediopago) {

        MedioPago medioPago = repositorioMP.findById(idmediopago)
                        .orElseThrow(() -> new ResourceNotFoundException("No existe el medio de pago con el ID: " + idmediopago));

        renta.setMedioPago(medioPago);

        renta.setEstado("En revisión");

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

        String fecha = fechaActual.obtenerFechaActual();

        // Asignación de fecha

        // ---- Si la renta se encuentra en revisión
        if(renta.getEstado().equals("En revisión")) {
            // y se desea abrirla
            if (detallesRenta.getEstado().equals("Abierto")) {
                renta.setInicio(fecha);
            // y se desea cerrarla
            } else if (detallesRenta.getEstado().equals("Cerrado")) {
                renta.setInicio(fecha);
                renta.setFin(fecha);
            }
        // ---- Si la renta se encuentra abierta
        } else if (renta.getEstado().equals("Abierto")) {
            // y se desea cerrarla
            if (detallesRenta.getEstado().equals("Cerrado")) {
                renta.setFin(fecha);
            // y se desea pasarla a revisión
            } else if (detallesRenta.getEstado().equals("En revisión")) {
                renta.setInicio(null);
                renta.setFin(null);
            }
        // ---- Si la renta se encuentra cerrada
        } else if (renta.getEstado().equals("Cerrado")) {
            // y se desea abrirla
            if (detallesRenta.getEstado().equals("Abierto")) {
                renta.setInicio(fecha);
                renta.setFin(null);
            // y se desea pasarla a revisión
            } else if (detallesRenta.getEstado().equals("En revisión")) {
                renta.setInicio(null);
                renta.setFin(null);
            }
        }

        // Asignación de nuevo estado
        renta.setEstado(detallesRenta.getEstado());

        Renta rentaActualizada = repositorio.save(renta);

        return ResponseEntity.ok(rentaActualizada);
    }

    @DeleteMapping("/rentas/{id}")
    public String eliminarRenta() {
        return "No es posible eliminar alquileres";
    }

    @DeleteMapping("/rentas")
    public String eliminarRentas() {
        return "No es posible eliminar alquileres";
    }


}
