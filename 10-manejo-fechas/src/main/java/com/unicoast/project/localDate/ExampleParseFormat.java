package com.unicoast.project.localDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/*
 * CONCEPTO CLAVE: Parseo seguro y manejo de DateTimeParseException
 * PROPÓSITO: Demostrar la conversión de texto libre a objetos de fecha estructurados y la captura de errores de formato.
 * NOTAS Y BUENAS PRÁCTICAS:
 * - Patrones de texto:
 *   - 'EEEE': Nombre completo del día de la semana (por ejemplo, "lunes").
 *   - 'dd': Día del mes en dos dígitos (01-31).
 *   - 'MMM': Abreviatura del mes (por ejemplo, "oct" u "oct.").
 *   - 'yyyy': Año en cuatro dígitos.
 * - Sensibilidad a la configuración regional (Locale):
 *   Al interpretar palabras ("lunes", "oct"), el formateador debe coincidir con el idioma del texto.
 *   Si el formateador no tiene configurado explícitamente Locale.forLanguageTag("es") y el sistema operativo
 *   está en otro idioma (como inglés), el análisis fallará lanzando DateTimeParseException.
 * - Manejo robusto de errores: DateTimeParseException provee información detallada sobre el índice
 *   del caracter exacto donde falló la interpretación (e.getErrorIndex()).
 */
public class ExampleParseFormat {
    public static void main(String[] args) {
        /*
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        LocalDateTime currentDateTime = LocalDateTime.now();

        System.out.println(today);
        System.out.println(now);
        System.out.println(currentDateTime);

        try {
            String dateText = "2026/10/18";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

            LocalDate parsedDate = LocalDate.parse(dateText, formatter);

            System.out.println(parsedDate);
        } catch (DateTimeParseException e) {
            System.out.println("Error formato: " + e.getMessage());
        }
         */

        try {
            String dateText = "lunes 19 oct 2026";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd MMM yyyy");

            LocalDate parsedDate = LocalDate.parse(dateText, formatter);

            System.out.println(parsedDate.format(formatter));
        } catch (DateTimeParseException e) {
            System.out.println("Error formato: " + e.getMessage());
        }
    }
}
