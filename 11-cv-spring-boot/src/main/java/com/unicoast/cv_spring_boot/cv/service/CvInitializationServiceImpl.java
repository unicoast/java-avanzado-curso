package com.unicoast.cv_spring_boot.cv.service;

import com.unicoast.cv_spring_boot.cv.model.CvData;
import com.unicoast.cv_spring_boot.cv.model.Education;
import com.unicoast.cv_spring_boot.cv.model.Experience;
import com.unicoast.cv_spring_boot.cv.model.PersonalDetails;
import com.unicoast.cv_spring_boot.cv.model.Skill;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * Servicio que inicializa los datos de ejemplo del currículum.
 * Muestra tres formas de crear listas en Java:
 * - Collections.singletonList(): inmutable, para un solo elemento.
 * - List.of(): inmutable, para varios elementos.
 * - Arrays.asList(): tamaño fijo, permite modificar valores con set().
 */
@Service
public class CvInitializationServiceImpl implements CvInitializationService {

    @Override
    public CvData initializeCvData() {
        CvData cvData = new CvData();

        // Datos personales
        PersonalDetails personalDetails = new PersonalDetails();
        personalDetails.setFirstName("Nicolás");
        personalDetails.setLastName("Astudillo");
        personalDetails.setEmail("nicolas@unicoast.dev");
        personalDetails.setProfessionalProfile("Desarrollador Backend");
        cvData.setPersonalDetails(personalDetails);

        // Educación: lista inmutable de un solo elemento
        Education education = new Education();
        education.setInstitution("Universidad Técnica Federico Santa María");
        education.setDegree("Técnico Universitario en Informática");
        education.setDescription("Desarrollo/Programación de aplicaciones y sistemas informáticos");
        education.setPeriod("2020 - 2022");
        cvData.setEducations(Collections.singletonList(education));

        // Experiencia laboral: lista inmutable de múltiples elementos
        Experience experience1 = new Experience();
        experience1.setJobTitle("Desarrollador Backend");
        experience1.setCompany("SOLEM S.A.");
        experience1.setPeriod("Noviembre 2025 - Mayo 2026");

        Experience experience2 = new Experience();
        experience2.setJobTitle("Desarrollador Full Stack");
        experience2.setCompany("SOLEM S.A.");
        experience2.setPeriod("Mayo 2022 - Noviembre 2025");
        cvData.setExperiences(List.of(experience1, experience2));

        // Habilidades técnicas: lista de tamaño fijo
        Skill skill1 = new Skill();
        skill1.setName("Java");
        skill1.setLevel("Intermedio");

        Skill skill2 = new Skill();
        skill2.setName("Spring Boot");
        skill2.setLevel("Intermedio");
        cvData.setSkills(Arrays.asList(skill1, skill2));

        return cvData;
    }
}
