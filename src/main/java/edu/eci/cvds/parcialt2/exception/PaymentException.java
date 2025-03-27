package edu.eci.cvds.parcialt2.exception;

public class PaymentException extends Exception {
    public static final String INVALID_TOTAL = "Invalid total";

    public PaymentException(String message) {
        super(message);
    }

}
