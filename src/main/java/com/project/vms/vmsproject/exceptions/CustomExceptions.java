package com.project.vms.vmsproject.exceptions;

public class CustomExceptions {
    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }
    public static class InvalidCredentialsException extends RuntimeException {
        public InvalidCredentialsException(String message) {
            super(message);
        }
    }
    public static class ProductNotFoundException extends RuntimeException {
        public ProductNotFoundException(String message) {
            super(message);
        }
    }
    public static class CveNotFoundException extends RuntimeException {
        public CveNotFoundException(String message) {
            super(message);
        }
    }
    public static class EmailAlreadyExistsException extends RuntimeException {
        public EmailAlreadyExistsException(String message) {
            super(message);
        }
    }
    public static class InvalidPasswordException extends RuntimeException {
        public InvalidPasswordException(String message) {
            super(message);
        }
    }
    public static class GeneralException extends RuntimeException {
        public GeneralException(String message) {
            super(message);
        }
    }
}
