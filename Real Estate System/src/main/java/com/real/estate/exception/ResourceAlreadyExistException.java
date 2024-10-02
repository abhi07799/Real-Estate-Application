package com.real.estate.exception;

public class ResourceAlreadyExistException extends RuntimeException
{
    public ResourceAlreadyExistException(String message)
    {
        super(message);
    }
}
