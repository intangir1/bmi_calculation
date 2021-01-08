package com.likhtman.bmi.Exceptions;

public class FloatOverflowException extends ArithmeticException {
    public FloatOverflowException(String errorMessage) {
        super(errorMessage);
    }
}
