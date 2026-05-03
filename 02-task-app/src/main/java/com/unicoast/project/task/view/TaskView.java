package com.unicoast.project.task.view;

import com.unicoast.project.task.controller.TaskController;
import com.unicoast.project.task.exception.TaskException;
import com.unicoast.project.task.exception.TaskValidationException;
import com.unicoast.project.task.model.Task;

import java.util.Scanner;

// Vista: interfaz de usuario por consola (patrón MVC)
// Se encarga de mostrar el menú, leer datos del usuario y delegar al controller
public class TaskView {
    private final TaskController taskController;
    private final Scanner scanner; // Lee la entrada del usuario desde la consola

    public TaskView(TaskController taskController) {
        this.taskController = taskController;
        this.scanner = new Scanner(System.in);
    }

    // Menú principal: bucle infinito que muestra opciones hasta que el usuario elija salir
    public void showMenu(){
        while (true){
            System.out.println("\nGestión de Tareas");
            System.out.println("1. Crear Tarea");
            System.out.println("2. Eliminar Tarea");
            System.out.println("3. Actualizar Tarea");
            System.out.println("4. Listar Tareas");
            System.out.println("5. Actualizar Estado de Tarea");
            System.out.println("6. Listar Tareas Completadas");
            System.out.println("7. Listar Tareas Pendientes");
            System.out.println("8. Salir");
            System.out.println("Seleccione una opción:");

            String option = scanner.nextLine();
            // Switch con arrow syntax (Java 14+): no necesita break
            switch (option) {
                case "1" -> createTask();
                case "2" -> deleteTask();
                case "3" -> updateTask();
                case "4" -> showTasks();
                case "5" -> updateCompletedTask();
                case "6" -> showCompletedTasks();
                case "7" -> showPendingTasks();
                case "8" -> {
                    System.out.println("Saliendo...");
                    return;
                }
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    // Crear tarea: pide datos al usuario, los pasa al controller
    public void createTask(){
        try {
            System.out.println("--- Crear Nueva Tarea ---");

            Task task =  getTaskInput();

            taskController.createTask(task.getId(), task.getTitle(), task.getDescription(), task.getCompleted());

            System.out.println("Tarea creada exitosamente");
        } catch (TaskValidationException | TaskException e) {
            // Multi-catch: captura ambas excepciones con el mismo bloque
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            // Catch genérico: cualquier otro error no esperado
            System.out.println("Error inesperado. Por favor contacte con el soporte");
            e.printStackTrace();
        }
    }

    // Eliminar tarea: pide el ID y delega al controller
    public void deleteTask(){
        try {
            System.out.println("--- Eliminar Tarea ---");

            System.out.println("Ingrese el Id de la tarea a eliminar");
            String id = scanner.nextLine();

            this.taskController.deleteTask(id);

            System.out.println("Tarea eliminada exitosamente");
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado. Por favor contacte con el soporte");
            e.printStackTrace();
        }
    }

    // Mostrar tareas: delega directamente al controller (no necesita pedir datos)
    public void showTasks(){
        try {
            System.out.println("\n--- Lista de Tareas ---");

            this.taskController.showTasks();
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado. Por favor contacte con el soporte");
            e.printStackTrace();
        }
    }

    // Actualizar tarea: pide todos los datos (reemplazo completo) y delega al controller
    public void updateTask(){
        try {
            System.out.println("--- Actualizar Tarea ---");

            Task task =  getTaskInput();

            taskController.updateTask(task.getId(), task.getTitle(), task.getDescription(), task.getCompleted());

            System.out.println("Tarea actualizada exitosamente");
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado. Por favor contacte con el soporte");
            e.printStackTrace();
        }
    }

    // Actualizar solo el estado (completed) de una tarea existente
    // A diferencia de updateTask(), NO pide todos los campos, solo el ID y el nuevo estado
    public void updateCompletedTask(){
        try {
            System.out.println("--- Actualizar Estado de Tarea ---");

            // Pedir el ID de la tarea a modificar
            String id;
            do {
                System.out.println("Ingrese el Id de la tarea");
                id = scanner.nextLine().trim();
                if (id.isEmpty()) {
                    System.out.println("El Id no puede estar vacío.");
                }
            } while (id.isEmpty());

            // Pedir el nuevo estado
            String completedInput;
            do {
                System.out.println("Ingrese el estado (true/false)");
                completedInput = scanner.nextLine().trim().toLowerCase();
                if (!completedInput.equals("true") && !completedInput.equals("false")) {
                    System.out.println("Solo se permite 'true' o 'false'.");
                }
            } while (!completedInput.equals("true") && !completedInput.equals("false"));

            taskController.updateCompletedTask(id, Boolean.parseBoolean(completedInput));

            System.out.println("Tarea actualizada exitosamente");
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado. Por favor contacte con el soporte");
            e.printStackTrace();
        }
    }

    // Mostrar solo tareas completadas
    public void showCompletedTasks(){
        try {
            System.out.println("\n--- Tareas Completadas ---");

            this.taskController.showCompletedTasks();
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado. Por favor contacte con el soporte");
            e.printStackTrace();
        }
    }

    // Mostrar solo tareas pendientes
    public void showPendingTasks(){
        try {
            System.out.println("\n--- Tareas Pendientes ---");

            this.taskController.showPendingTasks();
        } catch (TaskValidationException | TaskException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado. Por favor contacte con el soporte");
            e.printStackTrace();
        }
    }

    // Método privado reutilizable: lee los 4 campos de una tarea desde la consola
    // Se usa en createTask() y updateTask() para no duplicar código
    // Cada campo tiene un do-while que repite la pregunta hasta recibir un valor válido
    private Task getTaskInput(){
        String id;
        do {
            System.out.println("Ingresar Id");
            id = scanner.nextLine().trim();
            if (id.isEmpty()) {
                System.out.println("El Id no puede estar vacío.");
            }
        } while (id.isEmpty());

        String title;
        do {
            System.out.println("Ingrese el título");
            title = scanner.nextLine().trim();
            if (title.isEmpty()) {
                System.out.println("El título no puede estar vacío.");
            }
        } while (title.isEmpty());

        String description;
        do {
            System.out.println("Ingrese la descripción");
            description = scanner.nextLine().trim();
            if (description.isEmpty()) {
                System.out.println("La descripción no puede estar vacía.");
            }
        } while (description.isEmpty());

        String completedInput;
        do {
            System.out.println("Ingrese el estado (true/false)");
            completedInput = scanner.nextLine().trim().toLowerCase();
            if (!completedInput.equals("true") && !completedInput.equals("false")) {
                System.out.println("Solo se permite 'true' o 'false'.");
            }
        } while (!completedInput.equals("true") && !completedInput.equals("false"));

        // Boolean.parseBoolean: convierte "true" a true, cualquier otra cosa a false
        Boolean completed = Boolean.parseBoolean(completedInput);

        return new Task(id, title, description, completed);
    }
}
