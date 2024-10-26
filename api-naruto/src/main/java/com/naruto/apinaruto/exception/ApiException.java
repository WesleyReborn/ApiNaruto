package com.naruto.apinaruto.exception;

// Classe personalizada de exceção que estende RuntimeException
public class ApiException extends RuntimeException{
    // Construtor que recebe uma mensagem de erro e a causa original (Throwable)
    public ApiException(String message, Throwable error) {
        // Chama o construtor da classe RuntimeException, passando a mensagem e o erro
        super(message, error);
    }
}
