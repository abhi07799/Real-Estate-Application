package com.real.estate.exception;

public class NoResourceAvailableException extends RuntimeException
{
    public NoResourceAvailableException(String message)
    {
        super(message);
    }
}
