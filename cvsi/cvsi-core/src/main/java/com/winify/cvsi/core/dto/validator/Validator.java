package com.winify.cvsi.core.dto.validator;

abstract class Validator<T> implements IValidator<T>{
    
    private String message;

    Validator() {
        this.message = "OK";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}