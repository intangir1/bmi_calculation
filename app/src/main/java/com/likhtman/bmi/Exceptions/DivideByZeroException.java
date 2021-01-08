package com.likhtman.bmi.Exceptions;

public class DivideByZeroException extends ArithmeticException{
    public DivideByZeroException(String errorMessage) {
        super(errorMessage);
    }
}