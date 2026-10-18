package com.unicoast.project.localDate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

/*
 * CONCEPTO CLAVE: Internacionalización (i18n) de fechas con Locale y TextStyle
 * PROPÓSITO: Obtener representaciones textuales de componentes de tiempo (mes, día de la semana)
 *            traducidos a un idioma y región específicos (español de España en este caso).
 * NOTAS Y BUENAS PRÁCTICAS:
 * - Locale.Builder: Construye instancias de java.util.Locale siguiendo el estándar IETF BCP 47,
 *   siendo la alternativa recomendada frente a los constructores clásicos de Locale.
 * - TextStyle: Controla la longitud de la cadena generada:
 *   - TextStyle.FULL: Nombre completo (por ejemplo, "octubre", "domingo").
 *   - TextStyle.SHORT: Abreviatura común (por ejemplo, "oct.", "dom.").
 *   - TextStyle.NARROW: Letra o inicial mínima (por ejemplo, "O", "D").
 * - Estándar ISO-8601 numérico:
 *   - Meses: 1 = Enero ... 12 = Diciembre.
 *   - Días de semana: 1 = Lunes ... 7 = Domingo.
 */
public class ExampleLocalDateSpanish {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();

        // Month es un enum que contiene los 12 meses del año
        Month month = date.getMonth();
        System.out.println("Número del mes: " + month.getValue());

        // Creación del Locale para idioma español y región España
        Locale locale = new Locale.Builder().setLanguage("es").setRegion("ES").build();

        // getDisplayName() traduce el valor del enum al idioma especificado por el Locale
        System.out.println("Mes en español: " + month.getDisplayName(TextStyle.FULL, locale));

        // DayOfWeek es un enum que contiene los 7 días de la semana (1 = Lunes, 7 = Domingo)
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        System.out.println("Número del día de la semana: " + dayOfWeek.getValue());
        System.out.println("Día de la semana en español: " + dayOfWeek.getDisplayName(TextStyle.FULL, locale));

        // Salida nativa de los enums (imprime el nombre en inglés y mayúsculas por defecto)
        System.out.println(dayOfWeek);
        System.out.println(month);
    }
}
