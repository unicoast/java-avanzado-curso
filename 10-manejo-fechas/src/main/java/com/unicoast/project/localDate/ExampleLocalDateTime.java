package com.unicoast.project.localDate;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/*
 * CONCEPTO CLAVE: java.time.LocalDateTime (Combinación de fecha y hora sin huso horario)
 * PROPÓSITO: Modelar una fecha y hora completas (año, mes, día, hora, minutos, segundos, nanosegundos)
 *            según el estándar ISO-8601 (AAAA-MM-DDTHH:mm:ss).
 * NOTAS Y BUENAS PRÁCTICAS:
 * - Ausencia de zona horaria: LocalDateTime describe una fecha y hora local (por ejemplo, "24 de abril de 2025 a las 20:45").
 *   No representa un punto absoluto en la línea del tiempo global a menos que se combine con un huso horario (ZoneId o ZoneOffset).
 * - Separador ISO 'T': El estándar ISO-8601 utiliza el caracter literal 'T' para delimitar el componente de fecha del componente horario.
 * - Encadenamiento inmutable: Las operaciones de cálculo temporal (plusDays, plusHours) se pueden encadenar
 *   consecutivamente generando nuevas instancias sin efectos secundarios.
 * - Formateadores reutilizables: DateTimeFormatter.ISO_LOCAL_DATE_TIME es una constante inmutable provista por el JDK
 *   optimizada para la serialización estándar internacional.
 */
public class ExampleLocalDateTime {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");
        System.out.println(localDateTime.format(formatter));

        localDateTime = LocalDateTime.of(2025, Month.APRIL, 24, 20, 45, 59);
        System.out.println(localDateTime);

        localDateTime = LocalDateTime.parse("2020-12-23T21:23:59");
        System.out.println(localDateTime);

        System.out.println(localDateTime.plusDays(1).plusHours(4));

        String format1 = localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        System.out.println("Format 1: " + format1);

        String format2 = localDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss a"));
        System.out.println("Format 2: " + format2);
    }
}
