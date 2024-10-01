package com.real.estate.exception;

import com.real.estate.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorDto> handleNoResourceFoundException(final NoResourceFoundException ex)
    {
        new ErrorDto();
        return new ResponseEntity<ErrorDto>(ErrorDto.builder().message(ex.getMessage()).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorDto> handleAnyException(final Exception ex)
    {
        new ErrorDto();
        return new ResponseEntity<ErrorDto>(ErrorDto.builder().message(ex.getMessage()).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
