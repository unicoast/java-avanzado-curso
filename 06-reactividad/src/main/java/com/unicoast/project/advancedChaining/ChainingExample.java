package com.unicoast.project.advancedChaining;

import io.reactivex.rxjava3.core.Observable;

/*
 * ENCADENAMIENTO AVANZADO EN RXJAVA: MAP vs FLATMAP
 *
 * Diferencia entre map() y flatMap():
 * 1. map():
 *    - Transforma un valor simple en otro valor simple (1 a 1).
 *    - Si una función devuelve un 'Observable', map() generaría un 'Observable<Observable<T>>'.
 *
 * 2. flatMap():
 *    - Transforma cada elemento en un nuevo 'Observable' y aplana (flatten) todos los flujos
 *      resultantes en un único stream de salida continuo.
 *    - Es ideal para encadenar llamadas asíncronas sucesivas.
 *
 * Observable.empty():
 * Crea un flujo que no emite ningún elemento y finaliza con 'onComplete()'.
 * Permite manejar la ausencia de datos sin retornar nulos.
 */
public class ChainingExample {
    public static void main(String[] args) {
        Observable<Student> studentObservable = Observable.just(
                new Student("Mario", 23),
                new Student("Juan", 33),
                new Student("Estela", 43)
        );

        /*
         * EJEMPLO ANTERIOR (Transformación simple con map):
         * Aquí solo se transformaba el objeto Student a String (su nombre en mayúsculas).
         *
        studentObservable
                .filter(student -> student.getAge()>21)
                .map(student -> student.getName().toUpperCase())
                .subscribe(System.out::println);
         */

        /*
         * EJEMPLO ACTUAL (Transformación aplanada con flatMap):
         * Por cada estudiante mayor a 21, se ejecuta el método que retorna otro Observable<String>.
         * flatMap desenvuelve y unifica las materias emitidas en un único flujo directo.
         */
        studentObservable
                .filter(student -> student.getAge()>21)
                .flatMap(student -> getSubjectsPerStudent(student.getName()))
                .subscribe(
                        s -> System.out.println("Materia: " + s),
                        error -> System.out.println(error.getMessage()),
                        () -> System.out.println("Fin")
                );
    }

    /*
     * Simula la búsqueda asíncrona de materias por estudiante.
     * Retorna un Observable<String> con las materias si coincide el nombre,
     * o Observable.empty() si no existen materias para dicho estudiante.
     */
    public static Observable<String> getSubjectsPerStudent(String name){
        if(name.equalsIgnoreCase("Juan")){
            return Observable.just("Programación 3", "Lenguaje");
        }
        return Observable.empty();
    }
}
