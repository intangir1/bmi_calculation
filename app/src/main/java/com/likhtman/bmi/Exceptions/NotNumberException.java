package com.likhtman.bmi.Exceptions;

public class NotNumberException extends NumberFormatException{
    public NotNumberException(String errorMessage) {
        super(errorMessage);
    }
}