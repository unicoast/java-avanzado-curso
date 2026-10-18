package com.unicoast.project.zonedDateTime;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/*
 * CONCEPTO CLAVE: Cálculo de itinerarios y vuelos con ZonedDateTime
 * PROPÓSITO: Calcular la hora local de llegada de un vuelo internacional sumando la duración
 *            del viaje y convirtiendo el instante resultante a la zona horaria de destino.
 * NOTAS Y BUENAS PRÁCTICAS:
 * - Secuencia de cálculo:
 *   1. departureTime.plusHours(10): Añade la duración del trayecto en la línea de tiempo global.
 *   2. withZoneSameInstant(arrivalZone): Proyecta ese instante exacto en la zona horaria de Londres.
 * - Patrón 'z' en DateTimeFormatter:
 *   El especificador 'z' formatea el nombre o abreviatura oficial de la zona horaria (ej. PDT, BST, GMT+1).
 */
public class FlightScheduler {
    public static void main(String[] args) {
        ZoneId departureZone = ZoneId.of("America/Los_Angeles");
        ZoneId arrivalZone = ZoneId.of("Europe/London");

        ZonedDateTime departureTime = ZonedDateTime.now(departureZone).plusHours(5);

        System.out.println("Hora de salida en Los Ángeles: " + departureTime);

        ZonedDateTime arrivalTime = departureTime.plusHours(10).withZoneSameInstant(arrivalZone);

        System.out.println("Hora de llegada a Londres: " + arrivalTime);

        /*
         * Patrón con zona horaria:
         * El símbolo 'z' añade el nombre o sigla de la zona (ej. PDT para Los Ángeles, BST para Londres).
         */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

        System.out.println("Formateado: " + departureTime.format(formatter));
        System.out.println("Formateado: " + arrivalTime.format(formatter));

    }
}
