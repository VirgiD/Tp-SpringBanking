package com.ar.cac.homebanking.exceptions;

public class AtmNotFoundException extends  RuntimeException {

    public AtmNotFoundException(String message) {
        super(message);
    }

}
