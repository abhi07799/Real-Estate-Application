package com.real.estate.exception;

public class NoResourceFoundException extends RuntimeException
{
    public NoResourceFoundException(String message)
    {
        super(message);
    }
}
