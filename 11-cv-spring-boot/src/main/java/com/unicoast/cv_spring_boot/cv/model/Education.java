package com.unicoast.cv_spring_boot.cv.model;

import lombok.Data;

/*
 * Modelo para registros de formación académica.
 */
@Data
public class Education {
    private String institution;
    private String degree;
    private String period; // Período de estudio (ej: "2020 - 2022")
    private String description; // Detalle opcional de la carrera o logros
}
