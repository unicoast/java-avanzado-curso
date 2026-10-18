package com.unicoast.project.localDate;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

/*
 * CONCEPTO CLAVE: Operaciones temporales, comparaciones y diferencia entre Period y Duration
 * PROPÓSITO: Demostrar la manipulación aritmética de fechas y horas, la evaluación de condiciones temporales
 *            y el cálculo preciso de diferencias entre instantes.
 * NOTAS Y BUENAS PRÁCTICAS:
 * - Period vs Duration (Distinción fundamental):
 *   - Period: Modela cantidades de tiempo basadas en FECHAS (años, meses, días). Se utiliza con LocalDate.
 *     Ejemplo: Period.between(start, end).
 *   - Duration: Modela cantidades de tiempo basadas en TIEMPO EXACTO (horas, minutos, segundos, nanosegundos).
 *     Se utiliza con LocalTime, LocalDateTime o Instant.
 *     Regla crítica: Intentar usar Duration con LocalDate lanzará UnsupportedTemporalTypeException
 *     porque LocalDate no posee componentes de segundos ni horas.
 * - Comparaciones con semántica clara: isBefore(), isAfter() e isEqual() evitan conversiones manuales a milisegundos
 *   y ofrecen una lectura natural del flujo condicional.
 * - Cálculo de minutos restantes: diff.toMinutes() % 60 obtiene los minutos sobrantes tras calcular las horas totales.
 */
public class ExampleOperations {
    public static void main(String[] args) {
        /*
        LocalDate today = LocalDate.now();
        LocalDate tenDaysLater = today.plusDays(10);
        LocalDate fiveDaysBefore = today.minusDays(5);

        System.out.println(today);
        System.out.println(tenDaysLater);
        System.out.println(fiveDaysBefore);

        System.out.println("-------------------------");

        LocalTime now = LocalTime.now();
        LocalTime twoHoursLater = now.plusHours(2);
        LocalTime fiveMinutesBefore = now.minusMinutes(5);

        System.out.println(now);
        System.out.println(twoHoursLater);
        System.out.println(fiveMinutesBefore);
         */

        // Comparación de fechas
        /*
        LocalDate today = LocalDate.now();
        LocalDate deliveryDate = LocalDate.of(2026, 10, 19);

        if (today.isBefore(deliveryDate)) {
            System.out.println("Aún no es la fecha de entrega");
        } else if (today.isAfter(deliveryDate)) {
            System.out.println("Ya pasó la fecha de entrega");
        } else {
            System.out.println("Hoy es la fecha de entrega");
        }
        */

        /*
        LocalTime now = LocalTime.now();
        LocalTime classStartTime = LocalTime.of(13, 30); // 14:30 hs

        if (now.isBefore(classStartTime)) {
            System.out.println("La clase todavía no comenzó.");
        } else if (now.isAfter(classStartTime)) {
            System.out.println("Ya empezó o terminó la clase.");
        } else {
            System.out.println("¡Está comenzando la clase ahora!");
        }

        System.out.println("Hora actual: " + now);
        System.out.println("Hora de la clase: " + classStartTime);
         */


        /*
        LocalDate start = LocalDate.of(2024, 12, 25);
        LocalDate end = LocalDate.of(2025, 5, 30);

        Period diff = Period.between(start, end);

        System.out.println("Diferencia: " + diff.getMonths() + " meses " + diff.getDays() + " Días");
         */


        LocalTime start = LocalTime.of(9, 15);   // 09:15 hs
        LocalTime end = LocalTime.of(14, 45);    // 14:45 hs

        Duration diff = Duration.between(start, end);

        long hours = diff.toHours();
        long minutes = diff.toMinutes() % 60;

        System.out.println("Diferencia: " + hours + " horas y " + minutes + " minutos");
    }
}
