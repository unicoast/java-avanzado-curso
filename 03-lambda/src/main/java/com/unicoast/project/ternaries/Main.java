package com.unicoast.project.ternaries;

/*
 * OPERADOR TERNARIO
 * Forma compacta de un if-else que RETORNA un valor en una sola línea.
 * Sintaxis: condición ? valor_si_true : valor_si_false
 *
 * Conexión con Programación Funcional: Las lambdas y streams usan
 * expresiones (que retornan valor), no sentencias (if-else tradicional).
 * El ternario es una expresión, por eso aparece constantemente dentro de lambdas.
 */
public class Main {
    public static void main(String[] args) {
        int age = 20;
        String message;

//        if (age >= 18) {
//            message = "Mayor de edad";
//        } else {
//            message = "Menor de edad";
//        }
//
//        System.out.println(message);

        // 1. Ternario simple: reemplaza el if-else anterior en una sola línea.
        // Sintaxis: condición ? expresión_si_verdadero : expresión_si_falso;
        message = age >= 18 ? "Mayor de edad" : "Menor de edad";
        System.out.println(message);

        int score = 75;
        String level;

//        if (score >= 90) {
//            level = "Excelente";
//        } else if (score >= 70) {
//            level = "Bueno";
//        } else {
//            level = "Regular";
//        }

        // 2. Ternario anidado: reemplaza el if / else-if / else anterior.
        level = score >= 90 ? "Excelente" : score >= 70 ? "Bueno" : "Regular";
        System.out.println(level);

        int accessLevel = 2;
        String accessMessage = "";

//        if (accessLevel >= 1) {
//            if (accessLevel >= 3) {
//                accessMessage = "Acceso total.";
//            } else {
//                accessMessage = "Acceso parcial.";
//            }
//        } else {
//            accessMessage = "Acceso denegado.";
//        }

        // 3. Ternario con condiciones compuestas: los paréntesis mejoran la legibilidad.
        accessMessage = accessLevel >= 1 ? (accessLevel >= 3 ? "Acceso total." : "Acceso parcial.") : "Acceso denegado.";
        System.out.println(accessMessage);

    }
}
