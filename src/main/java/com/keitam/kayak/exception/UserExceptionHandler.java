package com.keitam.kayak.exception;

public class UserExceptionHandler extends RuntimeException {

    public UserExceptionHandler(String userNotFound){
        System.out.println("\nNo user was found by the information provided: " + userNotFound);
    }

    @Override
    public synchronized Throwable fillInStackTrace(){
        return this;
    }

}
