package com.eas.demo.gestionalquileres.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FechaActual {

    public static String obtenerFechaActual() {
        Date fechaActual = new Date();

        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        // Formatear la fecha actual
        String fechaFormateada = formatoFecha.format(fechaActual);

        return fechaFormateada;

    }
}
