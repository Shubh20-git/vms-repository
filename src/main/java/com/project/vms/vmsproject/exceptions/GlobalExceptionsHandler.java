package com.project.vms.vmsproject.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionsHandler {
    @ExceptionHandler(CustomExceptions.UserNotFoundException.class)
    public String handleUserNotFoundException(CustomExceptions.UserNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(CustomExceptions.InvalidCredentialsException.class)
    public String handleInvalidCredentialsException(CustomExceptions.InvalidCredentialsException ex) {
        return ex.getMessage();
    }
    @ExceptionHandler(CustomExceptions.ProductNotFoundException.class)
    public String handleProductNotFoundException(CustomExceptions.ProductNotFoundException ex) {
        return ex.getMessage();
    }
    @ExceptionHandler(CustomExceptions.CveNotFoundException.class)
    public String handleCveNotFoundException(CustomExceptions.CveNotFoundException ex) {
        return ex.getMessage();
    }
    @ExceptionHandler(CustomExceptions.EmailAlreadyExistsException.class)
    public String handleEmailAlreadyExistsException(CustomExceptions.EmailAlreadyExistsException ex) {
        return ex.getMessage();
    }
    @ExceptionHandler(CustomExceptions.InvalidPasswordException.class)
    public String handleInvalidPasswordException(CustomExceptions.InvalidPasswordException ex) {
        return ex.getMessage();
    }
    @ExceptionHandler(CustomExceptions.GeneralException.class)
    public String handleGeneralException(CustomExceptions.GeneralException ex) {
        return ex.getMessage();
    }
}
