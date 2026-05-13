package com.unicoast.project;

public class Main {
    public static void main(String[] args) {

        /*
         * Creación de una Clase Anónima que implementa la interfaz Operation.
         * Aunque parece que se instancia la interfaz con "new Operation()", en realidad
         * Java crea en tiempo de compilación/ejecución una clase sin nombre que implementa la interfaz.
         * Esta sintaxis permite definir y crear el objeto al mismo tiempo ("al vuelo").
         */
        Operation operation = new Operation() {
            @Override
            public int operate(int a, int b) {
                return a + b;
            }
        };

        System.out.println(operation.operate(5, 3));

        /*
         * Expresión Lambda: La forma moderna y concisa de implementar interfaces funcionales (Java 8+).
         * Como 'Operation' tiene un solo método abstracto, Java infiere automáticamente los tipos de los parámetros.
         * '(a, b)' son los argumentos, '->' separa parámetros del cuerpo, y 'a * b' es el retorno implícito.
         */
        Operation multiply = (a, b) -> a * b;
        System.out.println(multiply.operate(4, 5));

        Operation subtract = (a, b) -> a - b;
        System.out.println(subtract.operate(4, 5));
    }
}

/*
 * Interfaz Funcional: Define un contrato para operaciones matemáticas de dos operandos.
 * La anotación @FunctionalInterface le indica al compilador que verifique que exista
 * estrictamente un único método abstracto (SAM = Single Abstract Method: si tiene más de uno, deja de ser funcional).
 * Si se agrega otro método sin cuerpo, el compilador arrojaría un error.
 */
@FunctionalInterface
interface Operation {
    int operate(int a, int b);

    // Métodos 'default': Tienen cuerpo y NO cuentan como método abstracto.
    // Agregan comportamiento compartido sin afectar la interfaz funcional.
    default String show(){
        return "Soy una operación";
    }

    default String show2(){
        return "Soy una operación";
    }

    // Método 'static': Pertenece a la interfaz, no a la instancia. Puede retornar lambdas predefinidas.
    static Operation subtraction(){
        return (a, b) -> a - b;
    }
}
