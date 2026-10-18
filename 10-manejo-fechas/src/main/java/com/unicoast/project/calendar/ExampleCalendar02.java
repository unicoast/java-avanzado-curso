package com.unicoast.project.calendar;

import java.util.Calendar;

/*
 * CONCEPTO CLAVE: Sobrecarga Calendar.set(year, month, date) y meses en base cero
 * PROPÓSITO: Demostrar la asignación directa de año, mes y día en una sola instrucción con Calendar.
 * NOTAS Y BUENAS PRÁCTICAS:
 * - Desfase de mes: Al usar enteros para el mes, el valor 10 corresponde a NOVIEMBRE (mes 11 en el calendario civil),
 *   ya que Enero es 0, Febrero es 1, ..., Octubre es 9, Noviembre es 10 y Diciembre es 11.
 * - Buena práctica heredada: Cuando se utilice Calendar, es preferible emplear las constantes simbólicas
 *   como Calendar.OCTOBER o Calendar.NOVEMBER en lugar de literales numéricos para evitar confusiones.
 * - Enfoque moderno: En java.time.LocalDate.of(2026, 10, 18), los meses se numeran de forma natural de 1 a 12,
 *   o mediante el enum Month (Month.OCTOBER), eliminando este problema de raíz.
 */
public class ExampleCalendar02 {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();

        // Al pasar 10 como mes, Calendar interpreta Noviembre (base cero: 0 = Enero, 10 = Noviembre)
        cal.set(2026, 10, 18);

        // getTime() retorna la fecha y hora completas formateadas según la zona horaria del sistema
        System.out.println(cal.getTime());
    }
}
