package com.unicoast.project.task.controller;

import com.unicoast.project.task.exception.TaskException;
import com.unicoast.project.task.exception.TaskValidationException;
import com.unicoast.project.task.model.Task;
import com.unicoast.project.task.model.TaskRepository;

import java.util.List;

// Controller: punto de entrada para las operaciones de tareas
// Valida los datos ANTES de pasarlos al repositorio (separación de responsabilidades)
public class TaskController {
    // Dependencia inyectada por constructor (no crea su propio repositorio)
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Crear tarea: valida datos, construye el objeto Task y lo guarda
    public void createTask(String id, String title, String descripcion, Boolean completed) throws TaskValidationException, TaskException {
        validateTaskData(id, title, descripcion, completed);

        Task task = new Task(id, title, descripcion, completed);

        this.taskRepository.save(task);

        System.out.println("Tarea agregada: " + task);
    }

    // Eliminar tarea por ID
    public void deleteTask(String id) throws TaskValidationException, TaskException {
        if (id == null || id.trim().isEmpty()) {
            throw new TaskValidationException("El id de la tarea no puede ser nulo o vacío");
        }

        this.taskRepository.remove(id);
        System.out.println("Tarea eliminada con id: " + id);
    }

    // Mostrar todas las tareas
    public void showTasks() throws TaskValidationException, TaskException {
        List<Task> tasks = this.taskRepository.findAll();

        if (tasks.isEmpty()){
            throw new TaskValidationException("No hay tareas para mostrar");
        }

        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    // Mostrar tareas completadas
    public void showCompletedTasks() throws TaskValidationException, TaskException {
        List<Task> completedTasks = this.taskRepository.findCompletedTasks();

        for (Task task : completedTasks) {
            System.out.println(task);
        }
    }

    // Mostrar tareas pendientes
    public void showPendingTasks() throws TaskValidationException, TaskException {
        List<Task> pendingTasks = this.taskRepository.findPendingTasks();

        for (Task task : pendingTasks) {
            System.out.println(task);
        }
    }

    // Actualizar tarea: valida, crea un Task nuevo con los datos actualizados
    // y reemplaza el existente en el repositorio (reemplazo completo, como PUT en REST)
    public void updateTask(String id, String title, String descripcion, Boolean completed) throws TaskValidationException, TaskException {
        validateTaskData(id, title, descripcion, completed);

        // Crea un objeto Task NUEVO con los datos recibidos
        // El repositorio busca por ID y reemplaza el viejo con este nuevo
        Task updateTask = new Task(id, title, descripcion, completed);

        this.taskRepository.update(updateTask);

        System.out.println("Tarea actualizada: " + updateTask);
    }

    // Actualizar solo el estado de una tarea (no reemplaza el objeto completo)
    public void updateCompletedTask(String id, Boolean completed) throws TaskValidationException, TaskException {
        validateTaskData(id, completed);

        this.taskRepository.updateCompleted(id, completed);

        System.out.println("Tarea actualizada: " + id + " -> completed: " + completed);
    }

    // Validación centralizada: verifica que ningún campo sea null o vacío
    // Se reutiliza en createTask y updateTask (evita duplicar validaciones)
    private void validateTaskData(String id, String title, String descripcion, Boolean completed) throws TaskValidationException {
        if (id == null || id.trim().isEmpty()) {
            throw new TaskValidationException("El id de la tarea no puede ser nulo o vacío");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new TaskValidationException("El título de la tarea no puede ser nulo o vacío");
        }
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new TaskValidationException("La descripción de la tarea no puede ser nula o vacía");
        }
        if (completed == null) {
            throw new TaskValidationException("El estado de la tarea no puede ser nulo");
        }
    }

    // Sobrecarga de validateTaskData: valida solo id y completed (para updateCompletedTask)
    private void validateTaskData(String id, Boolean completed) throws TaskValidationException {
        if (id == null || id.trim().isEmpty()) {
            throw new TaskValidationException("El id de la tarea no puede ser nulo o vacío");
        }
        if (completed == null) {
            throw new TaskValidationException("El estado de la tarea no puede ser nulo");
        }
    }
}
