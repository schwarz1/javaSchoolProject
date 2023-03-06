package com.rosvitiazev.railways.exception;

public class CustomSQLException extends RuntimeException{
    public CustomSQLException(String message)
    {

    }
    public CustomSQLException(String message, Throwable throwable)
    {
        super(message, throwable);
    }
}
