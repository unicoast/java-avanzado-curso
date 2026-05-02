package com.unicoast.project.generics;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

//        Box<String> stringBox = new Box<>("Hola Nicolás");
//        System.out.println(stringBox.getValue());

//        Box<Integer> integerBox = new Box<>(25);
//        System.out.println(integerBox.getValue());

        Utility.printItem("Nicolás", 10);
        Utility.printItem(25);


        System.out.println(MathUtils.sum(2,10));

        List<String> names = new ArrayList<>();
        names.add("Nicolás");
        names.add("Novak");

        List<Integer> numbers = new ArrayList<>();
        numbers.add(25);
        numbers.add(38);

        printList(names);
        printList(numbers);

        sumNumber(numbers);
        addNumbers(numbers);
    }

    // El método printList usa el wildcard ? (unbounded wildcard) para aceptar listas de cualquier tipo
    // Esto permite imprimir listas genéricas como List<String> o List<Integer> sin especificar el tipo exacto,
    // manteniendo la seguridad de tipos en tiempo de compilación
    // Funciona bien para operaciones de lectura (como imprimir), pero no se pueden agregar elementos
    // a la lista usando este wildcard, ya que causaría un error de compilación
    public static void printList(List<?> list){
        for (Object o: list){
            System.out.println(o);
        }
    }

    // El método sumNumber usa el wildcard ? extends Number (upper bounded wildcard)
    // Permite aceptar listas de cualquier subtipo de Number (como Integer, Double)
    // Se puede leer como Number, pero no agregar elementos, ya que no se conoce el tipo exacto
    public static void sumNumber(List<? extends Number> numbers){
        double sum = 0;
        for (Number num: numbers){
            sum += num.doubleValue();
        }
        System.out.println("La suma de los números es: " + sum);
    }

    // El método addNumbers usa el wildcard ? super Integer (lower bounded wildcard)
    // Permite aceptar listas de Integer o sus supertipos (como Number, Object)
    // Se puede agregar Integer (o subtipos), pero al leer, solo se obtiene Object,
    // ya que no se conoce el tipo exacto de la lista
    public static void addNumbers(List<? super Integer> numbers){
        numbers.add(44);
        numbers.add(40);

        Object num = numbers.get(0);
        System.out.println(num);

        System.out.println("Edades agregadas a la lista: " + numbers);
    }
}
