package com.real.estate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OwnerResponseDto
{
    private long id;

    private String fullName;

    private String email;

    private String password;

    private LocalDateTime createdAt;

    private String role;
}
