package com.real.estate.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto
{
    private String message;
    private String exceptionStackTrace;

    public ErrorDto(String message)
    {
        this.message = message;
    }
}
