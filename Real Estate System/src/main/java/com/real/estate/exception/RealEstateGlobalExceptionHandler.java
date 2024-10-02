package com.real.estate.exception;

import com.real.estate.dto.ErrorDto;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class RealEstateGlobalExceptionHandler
{

    @ExceptionHandler(NoResourceAvailableException.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> handleNoResourceFoundException(final NoResourceAvailableException ex, final WebRequest request)
    {
        String path = request.getDescription(false).substring(4);
        new ErrorDto();
        ErrorDto error = ErrorDto.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("No Resource Found")
                .message(ex.getMessage())
                .path(path)
                .build();
        return new ResponseEntity<ErrorDto>(error, HttpStatus.NOT_FOUND);
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> handleAnyException(final Exception ex, final WebRequest request)
    {
        String path = request.getDescription(false).substring(4);
        new ErrorDto();
        ErrorDto error = ErrorDto.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Internal Server Error")
                .message(ex.getMessage())
                .path(path)
                .exceptionStackTrace(ExceptionUtils.getStackTrace(ex))
                .build();
        return new ResponseEntity<ErrorDto>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> handleMethodArgumentValidationException(MethodArgumentNotValidException ex, WebRequest request)
    {
        Map<String, String> errors = new HashMap<>();

        List<ObjectError> errorObjects = ex.getBindingResult().getAllErrors();

        for (int i = 0; i < errorObjects.size(); i++)
        {
            FieldError fieldError = (FieldError) errorObjects.get(i);
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        String path = request.getDescription(false).substring(4);
        new ErrorDto();
        ErrorDto error = ErrorDto.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message("Field Validation Error")
                .errors(errors)
                .path(path)
                .build();

        return new ResponseEntity<ErrorDto>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> handleResourceAlreadyFoundException(final ResourceAlreadyExistException ex, final WebRequest request)
    {
        String path = request.getDescription(false).substring(4);
        new ErrorDto();
        ErrorDto error = ErrorDto.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error("Resource Already Exist")
                .message(ex.getMessage())
                .path(path)
                .build();
        return new ResponseEntity<ErrorDto>(error, HttpStatus.CONFLICT);
    }
}
