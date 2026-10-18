package com.unicoast.project.calendar;

import java.util.Calendar;
import java.util.Date;

/*
 * CONCEPTO CLAVE: java.util.Calendar (Manipulación heredada de fechas en Java 1.1)
 * PROPÓSITO: Demostrar el uso de Calendar para asignar y modificar componentes individuales de fecha/hora.
 * NOTAS Y BUENAS PRÁCTICAS:
 * - Meses en base 0: Calendar numera los meses de 0 a 11 (0 = Enero, 11 = Diciembre).
 *   Esto produce habitualmente errores de desfase de un mes ("off-by-one errors") al trabajar con datos de usuario.
 * - Mutabilidad y falta de seguridad de hilos: Cada invocación a set() modifica la misma instancia interna.
 *   No es segura para concurrencia sin sincronización externa.
 * - Verbosis y complejidad: Construir una fecha exige múltiples llamadas o el uso de constantes de clase.
 * - Reemplazo moderno: Reemplazado a partir de Java 8 por java.time.LocalDate y java.time.LocalDateTime.
 */
public class ExampleCalendar {
    public static void main(String[] args) {
        // Calendar.getInstance() obtiene una instancia con la zona horaria y localización por defecto del sistema
        Calendar cal = Calendar.getInstance();

        // Configuración campo por campo
        cal.set(Calendar.YEAR, 2026);
        cal.set(Calendar.MONTH, Calendar.JANUARY); // El mes de Enero está indexado en 0
        cal.set(Calendar.DAY_OF_MONTH, 15);

        // getTime() extrae la representación equivalente en un objeto java.util.Date
        Date date = cal.getTime();
        System.out.println(date);

        // Mutabilidad riesgosa: modificar la instancia cal altera el estado para llamadas posteriores
        cal.set(Calendar.DAY_OF_MONTH, 5);
        date = cal.getTime();

        System.out.println(date);
    }
}
