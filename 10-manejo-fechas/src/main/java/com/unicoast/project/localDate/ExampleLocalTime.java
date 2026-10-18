package com.unicoast.project.localDate;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/*
 * CONCEPTO CLAVE: java.time.LocalTime (Manejo de hora sin fecha ni zona horaria)
 * PROPÓSITO: Modelar una hora del día con precisión de hasta nanosegundos (formato ISO-8601: HH:mm:ss.nnnnnnnnn).
 * NOTAS Y BUENAS PRÁCTICAS:
 * - Independencia de fecha y zona: LocalTime representa conceptos como "la hora del almuerzo (13:00)"
 *   o "la alarma (07:00)", independientes de cualquier día del calendario o huso horario.
 * - Inmutabilidad y aritmética: Métodos como plusMinutes(), minusHours() o plus(amount, ChronoUnit)
 *   devuelven siempre una nueva instancia calculada sin alterar la original.
 * - Constantes predefinidas: LocalTime.MIN (00:00:00), LocalTime.MAX (23:59:59.999999999),
 *   LocalTime.MIDNIGHT (00:00:00) y LocalTime.NOON (12:00:00).
 * - Comparaciones temporales: Métodos booleanos isBefore() e isAfter() permiten validar
 *   rangos y secuencias de tiempo con alta legibilidad.
 * - Patrones de formato:
 *   - 'HH': Formato militar o de 24 horas (00-23).
 *   - 'hh': Formato estándar de 12 horas (01-12).
 *   - 'a': Indicador AM / PM.
 */
public class ExampleLocalTime {
    public static void main(String[] args) {
        LocalTime now = LocalTime.now();

        System.out.println("Hora: " + now.getHour());
        System.out.println("Minutos: " + now.getMinute());
        System.out.println("Seg: " + now.getSecond());

        LocalTime coffeeBreak = LocalTime.of(16, 30);

        System.out.println(coffeeBreak);

        System.out.println("Suma: " + coffeeBreak.plus(1, ChronoUnit.MINUTES));

        System.out.println("Suma: " + coffeeBreak.plusMinutes(5));
        System.out.println("Restar: " + coffeeBreak.minusMinutes(5));

        System.out.println(LocalTime.of(7, 40).isBefore(LocalTime.parse("06:30")));

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");

        String time = coffeeBreak.format(dateTimeFormatter);

        System.out.println(time);

        LocalTime max = LocalTime.MAX;
        LocalTime min = LocalTime.MIN;
        LocalTime midnight = LocalTime.MIDNIGHT;

        System.out.println(max);
        System.out.println(min);
        System.out.println(midnight);
    }
}
