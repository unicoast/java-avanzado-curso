package com.unicoast.project.runnable;

/*
 * CONCURRENCIA EN JAVA: INTERFAZ RUNNABLE
 *
 * ¿Por qué 'implements Runnable' es PREFERIBLE a 'extends Thread'?
 *
 * 1. HERENCIA MÚLTIPLE DE INTERFACES:
 *    Java solo admite herencia simple de clases. Si se hereda de 'Thread', la clase no podrá
 *    extender de ninguna otra. Al implementar 'Runnable', la clase mantiene su capacidad de heredar de otra clase base.
 *
 * 2. SEPARACIÓN DE RESPONSABILIDADES (Desacoplamiento):
 *    Separa la TAREA que se quiere ejecutar (el objeto Runnable) del MECANISMO que la ejecuta (el objeto Thread).
 *
 * 3. REUSABILIDAD Y POOLS DE HILOS (Thread Pools):
 *    Las tareas Runnable se pueden reutilizar fácilmente en pools de hilos ('ExecutorService'),
 *    optimizando los recursos del sistema al no crear hilos manualmente.
 *
 * 4. PROGRAMACIÓN FUNCIONAL (Lambdas):
 *    Al ser una @FunctionalInterface (con un solo método abstracto 'run()'),
 *    se puede utilizar directamente con expresiones Lambda a partir de Java 8.
 */
public class RunnableExample implements Runnable {

    private String name;

    public RunnableExample(String name) {
        this.name = name;
    }

    /*
     * Método run(): Definición de la tarea que ejecutará el hilo en segundo plano.
     */
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(name + " Mensaje: " + i + " ejecutando en: " + Thread.currentThread().getName());

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {

        // Se pasa la instancia de Runnable (la tarea) directamente al constructor del objeto Thread
        Thread thread = new Thread(new RunnableExample("Proceso A"));
        Thread thread2 = new Thread(new RunnableExample("Proceso B"));

        // Se inician los hilos en paralelo
        thread.start();
        thread2.start();

        System.out.println("Fin del hilo principal");
    }
}
