package com.unicoast.project.localDate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
 * CONCEPTO CLAVE: Inspección de campos, Eras y Formateo/Parseo bidireccional con DateTimeFormatter
 * PROPÓSITO: Demostrar cómo consultar propiedades específicas de una fecha (día de semana, era, día del año)
 *            y la diferencia fundamental entre representar datos (String) y modelar datos (LocalDate).
 * NOTAS Y BUENAS PRÁCTICAS:
 * - Eras en ISO-8601: CE (Common Era / Era Común / d.C.) para años positivos y BCE (Before Common Era / a.C.) para años negativos.
 * - Separación de responsabilidades:
 *   - LocalDate es el tipo de dominio para realizar operaciones lógicas y cálculos de negocio.
 *   - String es únicamente la representación visual o formato de transferencia para el usuario o sistemas externos.
 * - Thread-Safety: DateTimeFormatter es inmutable y seguro para hilos, a diferencia del antiguo SimpleDateFormat.
 */
public class ExampleLocalDate02 {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2026, 10, 18);

        // Métodos de acceso para inspeccionar componentes individuales
        System.out.println("Día: " + date.getDayOfWeek());
        System.out.println("Mes: " + date.getMonth());
        System.out.println("Año: " + date.getYear());
        System.out.println("Día del año: " + date.getDayOfYear());
        System.out.println("Era: " + date.getEra()); // CE -> Common Era (después de Cristo)

        // Definición de un patrón de formato personalizado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // .format() convierte el objeto LocalDate en una cadena de texto (String) con el patrón indicado
        String formattedDate = date.format(formatter);
        System.out.println(formattedDate);

        // Años negativos: se representan con signo negativo en el método .of()
        LocalDate ancient = LocalDate.of(-44, 3, 15); // Fecha del asesinato de Julio César (Idus de marzo)
        System.out.println("Era antigua: " + ancient.getEra()); // BCE -> Before Common Era (antes de Cristo)

        /*
         * El método .format(formatter) sirve para convertir el objeto LocalDate en String para la capa visual.
         * Toda operación de cálculo, lógica o comparación debe realizarse sobre la instancia LocalDate y no sobre cadenas de texto.
         */
        /*
        String dateText = "18/10/2026";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate parsedDate = LocalDate.parse(dateText, formatter);
        System.out.println(parsedDate);
        */

        /*
         * Manejo de excepciones en parseo con texto descriptivo:
         * Al parsear nombres de días o meses en texto ("lunes", "octubre"), se debe especificar el Locale ("es", "ES").
         * Sin Locale explícito, DateTimeFormatter utiliza la configuración regional por defecto del sistema operativo.
         */
        /*
        try {
            String dateText = "lunes 19 octubre 2026";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy");

            LocalDate parsedDate = LocalDate.parse(dateText, formatter);

            System.out.println(parsedDate.format(formatter));
        } catch (DateTimeParseException e) {
            System.out.println("Error formato: " + e.getMessage());
        }
        */
    }
}
