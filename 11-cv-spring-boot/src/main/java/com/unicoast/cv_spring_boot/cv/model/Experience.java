package com.unicoast.cv_spring_boot.cv.model;

import lombok.Data;

/*
 * Modelo para registros de experiencia laboral.
 */
@Data
public class Experience {
    private String jobTitle;
    private String company;
    private String period; // Período laboral (ej: "Mayo 2022 - Noviembre 2025")
    private String description; // Responsabilidades y logros del puesto
}
