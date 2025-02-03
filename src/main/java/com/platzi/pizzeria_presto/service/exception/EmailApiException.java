package com.platzi.pizzeria_presto.service.exception;

public class EmailApiException extends RuntimeException{
    public EmailApiException(){
        super("Error sending email...");
    }
}
