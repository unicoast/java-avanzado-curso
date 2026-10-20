package com.unicoast.cv_spring_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Punto de entrada de la aplicación.
 * @SpringBootApplication inicializa el contexto y levanta el servidor web embebido.
 */
@SpringBootApplication
public class CvSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(CvSpringBootApplication.class, args);
	}

}
