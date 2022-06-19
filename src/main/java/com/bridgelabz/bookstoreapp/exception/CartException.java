package com.bridgelabz.bookstoreapp.exception;


public class CartException extends RuntimeException {
    public ExceptionTypes exceptionTypes;

    public CartException(ExceptionTypes exceptionTypes) {
        this.exceptionTypes = exceptionTypes;
    }

    public enum ExceptionTypes {
        BOOK_ALREADY_PRESENT("book exists in cart");
        public String errorMessage;
        ExceptionTypes(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}
