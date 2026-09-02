package com.unicoast.project.filterAndCondition;

import io.reactivex.rxjava3.core.Observable;

/*
 * OPERADORES DE FILTRADO Y CONDICIÓN EN RXJAVA 3
 *
 * Operadores clave:
 * 1. filter(Predicate): Deja pasar únicamente los elementos que cumplen la condición.
 * 2. distinct(): Elimina elementos repetidos en todo el flujo.
 * 3. take(count): Emite únicamente los primeros 'count' elementos y completa el stream.
 * 4. takeWhile(Predicate): Emite elementos mientras la condición sea verdadera;
 *    al primer elemento que no cumpla, corta y finaliza el flujo definitivamente.
 */
public class FilterAndCondition {
    public static void main(String[] args) {
        // filter
        Observable<Integer> ages = Observable.just(11, 21, 20, 20, 30, 12, 14, 18, 60, 70);

        // filter: filtra solo mayores a 21
        // ages.filter(age -> age > 21).subscribe(System.out::println);

        // distinc no repite: elimina elementos duplicados
        // ages.distinct().subscribe(System.out::println);

        // take toma 4 primeros: solo emite los 4 primeros elementos
        // ages.take(4).subscribe(System.out::println);

        // takewhile condicional corta el flujo: emite mientras sea < 21 y corta al encontrar 21
        ages.takeWhile(age -> age < 21)
                .subscribe(System.out::println);

    }
}
