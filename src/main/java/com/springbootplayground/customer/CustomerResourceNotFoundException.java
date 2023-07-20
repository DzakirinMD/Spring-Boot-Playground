package com.springbootplayground.customer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception for record not exist in DB
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomerResourceNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public CustomerResourceNotFoundException(String message){
        super(message);
    }
}
