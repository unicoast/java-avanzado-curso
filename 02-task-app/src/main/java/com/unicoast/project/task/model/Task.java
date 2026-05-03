package com.unicoast.project.task.model;

import java.util.Objects;

// Modelo Task: representa una tarea con id, título, descripción y estado
public class Task {
    private String id;
    private String title;
    private String description;
    private Boolean completed; // Boolean (wrapper) en vez de boolean (primitivo) para poder validar null

    public Task(String id, String title, String description, Boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    // equals y hashCode: dos tareas son iguales si tienen el MISMO ID
    // Esto permite detectar duplicados con List.contains() en save()
    // También hace que List.remove() funcione correctamente
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Misma referencia en memoria
        if (o == null || getClass() != o.getClass()) return false; // Null o distinta clase
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", completed=" + completed +
                '}';
    }
}
