package com.unicoast.project.zonedDateTime;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/*
 * CONCEPTO CLAVE: java.time.ZonedDateTime y ZoneId (Zonas horarias y conversión de instantes)
 * PROPÓSITO: Modelar fechas y horas asociadas a una región geográfica específica (base de datos IANA)
 *            y calcular la equivalencia horaria de un mismo instante entre diferentes husos horarios.
 * NOTAS Y BUENAS PRÁCTICAS:
 * - ZoneId: Representa la zona horaria geográfica ("America/Santiago").
 * - ZonedDateTime: Objeto inmutable con fecha, hora, zona horaria y desfase UTC.
 * - withZoneSameInstant: Convierte la hora a otra zona horaria manteniendo el mismo instante en el tiempo.
 */
public class ExampleZone {
    public static void main(String[] args) {
        /*
        ZoneId.getAvailableZoneIds().stream()
                .sorted()
                .forEach(System.out::println);
        */

        /*
        // https://en.wikipedia.org/wiki/List_of_tz_database_time_zones
        ZoneId.getAvailableZoneIds()
                .stream()
                .filter(id -> id.toLowerCase().contains("santiago"))
                .sorted()
                .forEach(System.out::println);
        */

        /*
        ZoneId santiago = ZoneId.of("America/Santiago");

        ZonedDateTime nowSantiago = ZonedDateTime.now(santiago);

        System.out.println(nowSantiago);

        ZonedDateTime madrid = ZonedDateTime.now(ZoneId.of("Europe/Madrid"));
        System.out.println(madrid);
        */

        ZoneId santiago = ZoneId.of("America/Santiago");

        ZonedDateTime nowSantiago =
                ZonedDateTime.of(2026, 10, 18, 15, 30, 0, 0, santiago);

        ZonedDateTime madrid = nowSantiago.withZoneSameInstant(ZoneId.of("Europe/Madrid"));

        System.out.println(nowSantiago);
        System.out.println(madrid);
    }
}
