package com.unicoast.cv_spring_boot.cv.controller;

import com.unicoast.cv_spring_boot.cv.model.CvData;
import com.unicoast.cv_spring_boot.cv.service.CvInitializationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/*
 * Controlador web para la captura y visualización del currículum vitae.
 * Aplica el patrón Post-Redirect-Get (PRG) para evitar reenvíos al refrescar.
 */
@Controller
@RequiredArgsConstructor
public class LandingController {

    private final CvInitializationService cvInitializationService;

    // Redirige la raíz al formulario de edición
    @GetMapping("/")
    public String redirectToForm() {
        return "redirect:/cv-form";
    }

    /*
     * Muestra el formulario con datos precargados.
     * Model transporta los datos iniciales hacia Thymeleaf en la petición GET actual.
     */
    @GetMapping("/cv-form")
    public String form(Model model) {
        CvData cvData = cvInitializationService.initializeCvData();
        model.addAttribute("cvData", cvData);
        return "cv-form";
    }

    /*
     * Procesa el formulario y redirige a la vista del currículum.
     * - @ModelAttribute: vincula automáticamente los datos del formulario a CvData.
     * - addFlashAttribute: guarda CvData temporalmente en sesión para sobrevivir a la redirección.
     */
    @PostMapping("/generate-cv")
    public String generateCv(@ModelAttribute CvData cvData, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("cvData", cvData);
        return "redirect:/cv-display";
    }

    /*
     * Renderiza la vista final del currículum.
     * Recupera CvData desde el atributo flash o carga datos base si se ingresa directamente.
     */
    @GetMapping("/cv-display")
    public String displayCvData(@ModelAttribute("cvData") CvData cvData, Model model) {
        if (cvData.getPersonalDetails() == null) {
            cvData = cvInitializationService.initializeCvData();
        }
        model.addAttribute("cvData", cvData);
        return "index";
    }
}
