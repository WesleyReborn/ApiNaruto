package com.naruto.apinaruto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Anotação que indica que esta classe é a principal da aplicação Spring Boot
@SpringBootApplication
public class ApiNarutoApplication {
	// O método principal (main) que serve como ponto de entrada da aplicação
	public static void main(String[] args) {
		// Método estático que inicializa a aplicação Spring Boot
		// Ele configura e executa a aplicação
		SpringApplication.run(ApiNarutoApplication.class, args);
	}
}
