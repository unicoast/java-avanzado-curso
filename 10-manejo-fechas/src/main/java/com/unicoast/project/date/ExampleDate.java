package com.unicoast.project.date;

import java.util.Date;

/*
 * CONCEPTO CLAVE: java.util.Date (API de fechas heredada / Legacy)
 * PROPÓSITO: Demostrar el funcionamiento de la clase histórica Date de Java, introducida en JDK 1.0.
 * NOTAS Y BUENAS PRÁCTICAS:
 * - Mutabilidad: Date es una clase mutable. Cualquier hilo que tenga acceso a la referencia
 *   puede alterar su estado mediante métodos como setTime(), generando condiciones de carrera
 *   (race conditions) e inconsistencias en aplicaciones concurrentes.
 * - Época Unix (Epoch Time): Representa el punto cero estándar de tiempo en informática:
 *   1 de enero de 1970 a las 00:00:00 UTC.
 *   - Valor 0: 01/01/1970 00:00:00 UTC.
 *   - Valores positivos: instantes posteriores a la época.
 *   - Valores negativos: instantes anteriores a la época.
 * - Obsolescencia: A partir de Java 8, se desaconseja el uso de java.util.Date para nueva lógica
 *   de negocio en favor de la moderna API java.time (Instant, LocalDate, ZonedDateTime).
 */
public class ExampleDate {
    public static void main(String[] args) {
        // Instancia que captura el instante actual del sistema en milisegundos
        Date date = new Date();
        System.out.println("Fecha original: " + date);

        // Problema de diseño: Date permite mutar su estado interno en cualquier momento.
        // Al modificar la misma referencia compartida, se rompe la seguridad en entornos multihilo.
        // El valor pasado en milisegundos calcula el tiempo relativo al 1 de enero de 1970 (época Unix).
        date.setTime(-84_000_000); // 84 millones de milisegundos antes del 01/01/1970 (~23.33 horas antes)
        System.out.println("Fecha modificada " + date);
    }
}
