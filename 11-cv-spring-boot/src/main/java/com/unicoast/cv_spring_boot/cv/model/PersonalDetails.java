package com.unicoast.cv_spring_boot.cv.model;

import lombok.Data;

/*
 * Modelo para datos personales y de contacto del postulante.
 */
@Data
public class PersonalDetails {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String province;
    private String postalCode;
    private String professionalProfile; // Resumen del perfil profesional
}
