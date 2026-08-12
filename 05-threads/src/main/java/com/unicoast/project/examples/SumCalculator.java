package com.unicoast.project.examples;

import java.util.concurrent.*;

/*
 * INTERFAZ CALLABLE Y RESULTADOS FUTUROS CON FUTURE
 *
 * A diferencia de 'Runnable' (cuyo método 'run()' no retorna valores ni lanza excepciones verificadas),
 * la interfaz 'Callable<V>' permite definir tareas asíncronas que retornan un resultado de tipo 'V'
 * y pueden lanzar excepciones durante su ejecución.
 *
 * Conceptos clave:
 * 1. Callable<V>: Interfaz funcional del paquete 'java.util.concurrent' con el método 'V call() throws Exception'.
 * 2. Future<V>: Objeto que representa el resultado futuro de una tarea asíncrona enviada a un 'ExecutorService'.
 *    - result.isDone(): Retorna 'true' si la tarea finalizó su ejecución.
 *    - result.get(): Bloquea el hilo actual hasta que la tarea termine y retorna el resultado computado.
 */
public class SumCalculator implements Callable<Integer> {
    private int number1;
    private int number2;

    public SumCalculator(int number1, int number2) {
        this.number1 = number1;
        this.number2 = number2;
    }

    /*
     * Método call(): Contiene la lógica de la tarea asíncrona y retorna la suma calculada.
     */
    @Override
    public Integer call() throws Exception {
        System.out.println("Tarea: Iniciando suma...");

        // Simula la ejecución de un cálculo pesado (1.5 segundos)
        Thread.sleep(1500);

        int sum = number1 + number2;

        System.out.println("Name: " + Thread.currentThread().getName());

        System.out.println("Tarea: Suma completada");
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Se crea un pool de hilos con tamaño fijo
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<Integer> sumTask = new SumCalculator(5, 60);

        // Future para obtener resultados de una tarea que se ejecuta en otro hilo
        Future<Integer> result = executor.submit(sumTask);

        // Polling: consulta periódica mediante isDone() mientras la tarea continúe en proceso
        while (!result.isDone()){
            System.out.println("Procesando...");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }

        // Obtiene el resultado devuelto por la tarea Callable tras su finalización
        System.out.println("Resultado = " + result.get());

        // Cierre ordenado del pool de hilos
        executor.shutdown();
    }
}
