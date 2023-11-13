package com.example.myshoppinglistapi.exceptions;

public class UserExistException extends RuntimeException {

    public UserExistException(String message){
        super(message);
    }
}
