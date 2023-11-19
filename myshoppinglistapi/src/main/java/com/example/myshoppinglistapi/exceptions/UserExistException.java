package com.example.myshoppinglistapi.exceptions;

public class UserExistException extends Exception {

    public UserExistException(String message){
        super(message);
    }
}
