# Currículum Vitae con Spring Boot y Thymeleaf

Ejemplo introductorio de una aplicación web MVC con **Spring Boot** y **Thymeleaf**. Permite cargar, editar y visualizar los datos de un currículum vitae en el navegador.

---

## Estructura del Proyecto

```text
11-cv-spring-boot
+-- src/main/java/com/unicoast/cv_spring_boot
|   +-- CvSpringBootApplication.java       # Punto de entrada que levanta la aplicación
|   +-- cv
|       +-- controller/                    # Rutas web (GET/POST) y control de vistas
|       |   +-- LandingController.java
|       +-- model/                         # Clases que estructuran los datos del CV
|       |   +-- CvData.java, PersonalDetails.java, Education.java, Experience.java, Skill.java
|       +-- service/                       # Lógica para inicializar los datos de ejemplo
|           +-- CvInitializationService.java
|           +-- CvInitializationServiceImpl.java
+-- src/main/resources
    +-- static/css/                        # Archivos estáticos directos al navegador (CSS)
    +-- templates/                         # Plantillas HTML dinámicas procesadas con Thymeleaf
```

---

## Flujo Web (Patrón Post-Redirect-Get)

1. **`GET /cv-form`:** Entrega el formulario. Usa `Model` para pasar los datos iniciales a la plantilla.
2. **`POST /generate-cv`:** `@ModelAttribute` captura los datos del formulario en un objeto `CvData`, y `addFlashAttribute` los guarda temporalmente en sesión para la redirección (HTTP 302).
3. **`GET /cv-display`:** Renderiza el currículum final con los datos recibidos. 

> La redirección evita que al presionar F5 en el navegador se reenvíe el formulario por error.

---

## Cómo Ejecutar

```powershell
# Windows
.\mvnw.cmd spring-boot:run

# Linux / macOS
./mvnw spring-boot:run
```

Navegador: [http://localhost:8080/](http://localhost:8080/)
