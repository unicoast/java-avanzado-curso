package com.unicoast.cv_spring_boot.cv.model;

import lombok.Data;

/*
 * Modelo para competencias técnicas y nivel de dominio.
 */
@Data
public class Skill {
    private String name;  // Nombre de la tecnología o habilidad
    private String level; // Nivel estimado (ej: "Básico", "Intermedio", "Avanzado")
}
