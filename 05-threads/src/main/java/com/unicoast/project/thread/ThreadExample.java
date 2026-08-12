package com.unicoast.project.thread;

/*
 * CONCURRENCIA Y HILOS (Threads) EN JAVA
 *
 * ¿Qué es un Hilo (Thread)?
 * Un hilo es la unidad mínima de ejecución dentro de un programa. Permite realizar
 * múltiples tareas de forma concurrente o en paralelo, aprovechando los procesadores multinúcleo.
 *
 * Creación de hilos mediante la clase 'Thread':
 * 1. La clase debe extender de 'Thread' (herencia).
 * 2. Se debe sobrescribir el método 'run()', que contiene la tarea a ejecutar en segundo plano.
 * 3. Se debe llamar al método 'start()' para crear e iniciar el nuevo hilo en el Sistema Operativo.
 */
public class ThreadExample extends Thread {
  
    private String name;

    public ThreadExample(String name) {
        this.name = name;
    }

    /*
     * Método run(): Punto de entrada del hilo.
     * Todo el código dentro de este método se ejecutará en un hilo de ejecución independiente.
     */
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            /*
             * Thread.currentThread().getName() obtiene el nombre del hilo en ejecución
             * asignado por la JVM o el Sistema Operativo (ej. "Thread-0", "Thread-1").
             */
            System.out.println(name + " Mensaje: " + i + " ejecutando en: " + Thread.currentThread().getName());

            try {
                /*
                 * Thread.sleep(2000): Pausa el hilo actual por 2000 milisegundos (2 segundos).
                 * Requiere capturar 'InterruptedException' por si otro hilo interrumpe la espera.
                 */
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("El hilo fue interrumpido: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        // Toda aplicación Java comienza en el hilo principal denominado "main".
        System.out.println("Inicio del hilo principal: " + Thread.currentThread().getName());

        // Instanciación de los objetos Thread (Estado: NEW / Nuevo)
        ThreadExample threadExample = new ThreadExample("Proceso A");
        ThreadExample threadExample2 = new ThreadExample("Proceso B");

        /*
         * DIFERENCIA CRÍTICA: start() vs run()
         * - start(): Solicita al Sistema Operativo crear un NUEVO hilo de ejecución y llamar a run() asíncronamente.
         * - run(): Si se llama directamente, se ejecuta de forma síncrona dentro del hilo actual ("main") sin concurrencia.
         */
        threadExample.start();
        threadExample2.start();

        // El hilo "main" continúa de forma asíncrona sin esperar a que Proceso A y Proceso B terminen.
        System.out.println("Fin del hilo principal");
    }
}
