package com.unicoast.project.task.model;

import com.unicoast.project.task.exception.TaskException;
import com.unicoast.project.task.persistence.TaskPersistence;

import java.util.ArrayList;
import java.util.List;

// Repositorio: almacena las tareas en memoria (List) y expone operaciones CRUD
public class TaskRepository {
    // Lista interna de tareas (simula una base de datos en memoria)
    private List<Task> tasks;

    // Constructor: carga las tareas desde el archivo JSON al iniciar
    // Si no existe el archivo, TaskPersistence retorna una lista vacía
    public TaskRepository() {
        tasks = TaskPersistence.loadTasks();
    }

    // Guardar: agrega una tarea a la lista
    public void save(Task task) throws TaskException {
        if (task == null){
            throw new TaskException("La tarea no puede ser nula");
        }
        // Verificar duplicados: contains() usa equals() de Task, que compara por ID
        if (tasks.contains(task)){
            throw new TaskException("La tarea ya existe en la lista");
        }

        tasks.add(task);
        TaskPersistence.saveTasks(tasks);
    }

    // Buscar por ID: recorre la lista y retorna la tarea o null si no existe
    public Task findById(String id){
        for (Task task : tasks) {
            if(task.getId().equals(id)){
                return task;
            }
        }
        return null;
    }

    // Buscar tareas completadas: filtra las que tienen completed = true
    public List<Task> findCompletedTasks() throws TaskException {
        List<Task> completedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if(task.getCompleted()){
                completedTasks.add(task);
            }
        }
        if (completedTasks.isEmpty()){
            throw new TaskException("No hay tareas completadas");
        }
        return completedTasks;
    }

    // Buscar tareas pendientes: filtra las que tienen completed = false
    public List<Task> findPendingTasks() throws TaskException {
        List<Task> pendingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if(!task.getCompleted()){
                pendingTasks.add(task);
            }
        }
        if (pendingTasks.isEmpty()){
            throw new TaskException("No hay tareas pendientes");
        }
        return pendingTasks;
    }

    // Eliminar por ID: busca la tarea y la elimina de la lista
    public void remove(String id) throws TaskException {
        Task task = findById(id);
        if (task == null){
            throw new TaskException("La tarea no puede ser nula");
        }
        if (!tasks.contains(task)){
            throw new TaskException("La tarea no existe en la lista");
        }
        tasks.remove(task);
        TaskPersistence.saveTasks(tasks);
    }

    // Eliminar por objeto: elimina directamente pasando la tarea
    public void remove(Task task) throws TaskException {
        if (task == null){
            throw new TaskException("La tarea no puede ser nula");
        }
        tasks.remove(task);
        TaskPersistence.saveTasks(tasks);
    }

    // Obtener todas las tareas
    public List<Task> findAll() throws TaskException {
        if (tasks.isEmpty()){
            throw new TaskException("No hay tareas en la lista");
        }
        return tasks;
    }

    // Buscar el índice de una tarea por su ID (retorna -1 si no existe)
    // Se usa en update() para saber EN QUÉ posición reemplazar
    public int findIndexById(String id){
        for (int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getId().equals(id)){
                return i;
            }
        }
        return -1; // Convención Java: -1 significa "no encontrado"
    }

    // Actualizar: reemplaza la tarea completa en la posición encontrada
    // tasks.set(index, task) -> sustituye el objeto en esa posición del ArrayList
    public void update(Task task) throws TaskException {
        if (task == null){
            throw new TaskException("La tarea no puede ser nula");
        }
        int index = findIndexById(task.getId());
        if(index == -1){
            throw new TaskException("El índice no es correcto");
        }
        tasks.set(index, task); // Reemplazo completo: el objeto viejo se descarta
        TaskPersistence.saveTasks(tasks);
    }

    // Actualizar solo el estado (completed) de una tarea sin reemplazar el objeto completo
    // A diferencia de update(), modifica UN campo del objeto existente con setCompleted()
    public void updateCompleted(String id, Boolean completed) throws TaskException {
        int index = findIndexById(id);
        if(index == -1){
            throw new TaskException("El índice no es correcto");
        }
        tasks.get(index).setCompleted(completed);
        TaskPersistence.saveTasks(tasks);
    }
}
