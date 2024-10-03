package com.real.estate.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminResponseDto
{
    private long id;

    private String fullName;

    private String email;

    private String password;

    private LocalDateTime createdAt;

    private String role;

    private String mobileNumber;
}
