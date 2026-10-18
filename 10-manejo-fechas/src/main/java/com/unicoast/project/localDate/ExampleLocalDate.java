package com.unicoast.project.localDate;

import java.time.LocalDate;

/*
 * CONCEPTO CLAVE: java.time.LocalDate e Inmutabilidad (Java 8+ / JSR-310)
 * PROPÓSITO: Representar una fecha civil (año, mes y día) sin hora ni zona horaria (formato ISO-8601: AAAA-MM-DD).
 * NOTAS Y BUENAS PRÁCTICAS:
 * - Inmutabilidad garantizada: A diferencia de java.util.Date y Calendar, todas las clases del paquete java.time
 *   son inmutables. Métodos como plusDays(), minusMonths() o withYear() devuelven una nueva instancia
 *   con el resultado, dejando la referencia original intacta.
 * - Seguridad en concurrencia (Thread-Safety): Al no tener estado mutable, múltiples hilos pueden compartir
 *   referencias a objetos LocalDate de forma segura sin requerir bloques synchronized ni locks.
 * - Meses basados en 1: En LocalDate.of(2026, 10, 18), el valor 10 corresponde exactamente a Octubre.
 */
public class ExampleLocalDate {
    public static void main(String[] args) {
        // Creación mediante método de fábrica estático .of(año, mes, día)
        LocalDate date = LocalDate.of(2026, 10, 18);
        System.out.println("Fecha original " + date);

        // Operación de suma de días: genera y retorna una nueva instancia calculada
        LocalDate updateDate = date.plusDays(5);
        System.out.println(updateDate);

        // Demostración de inmutabilidad: la instancia 'date' original no fue alterada
        System.out.println("Fecha original " + date);
    }
}
