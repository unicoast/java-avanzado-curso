package com.unicoast.cv_spring_boot.cv.model;

import lombok.Data;

import java.util.List;

/*
 * Modelo compuesto que agrupa todas las secciones del currículum vitae.
 * Permite a Thymeleaf enlazar formularios con colecciones anidadas.
 */
@Data
public class CvData {
    private PersonalDetails personalDetails;
    private List<Education> educations;
    private List<Experience> experiences;
    private List<Skill> skills;
}
