package com.unicoast.project.localDate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

/*
 * CONCEPTO CLAVE: Extracción concisa de fecha en español
 * PROPÓSITO: Demostrar la traducción directa del mes y día de la semana para componentes de interfaz de usuario.
 * NOTAS Y BUENAS PRÁCTICAS:
 * - Reutilización de Locale: En entornos de producción, la instancia de Locale debe ser compartida
 *   (por ejemplo, como constante de clase o inyectada desde el contexto de la solicitud HTTP) para evitar asignaciones repetitivas en memoria.
 * - Flexibilidad de presentación: Separar la lógica de negocio (LocalDate) de la presentación (TextStyle + Locale)
 *   permite que una misma fecha se renderice en múltiples idiomas según las preferencias de cada usuario.
 */
public class ExampleLocalDateSpanish02 {
    public static void main(String[] args) {
        // 1. Extracción y traducción del mes al español
        LocalDate date = LocalDate.now();
        Month month = date.getMonth();
        System.out.println("Número del mes: " + month.getValue());
        Locale locale = new Locale.Builder().setLanguage("es").setRegion("ES").build();
        System.out.println("Mes en español: " + month.getDisplayName(TextStyle.FULL, locale));

        // 2. Extracción y traducción del día de la semana al español
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        System.out.println("Número del día de la semana: " + dayOfWeek.getValue());
        System.out.println("Día de la semana: " + dayOfWeek.getDisplayName(TextStyle.FULL, locale));
    }
}
