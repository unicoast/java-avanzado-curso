package com.unicoast.cv_spring_boot.cv.service;

import com.unicoast.cv_spring_boot.cv.model.CvData;

/*
 * Contrato de servicio para la inicialización y preparación de datos del currículum.
 * Desacopla la lógica de negocio del controlador web.
 */
public interface CvInitializationService {
    CvData initializeCvData();
}
