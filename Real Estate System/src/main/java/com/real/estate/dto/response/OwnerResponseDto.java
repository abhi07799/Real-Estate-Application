package com.real.estate.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.real.estate.model.PropertyModel;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OwnerResponseDto
{
    private long id;

    private String fullName;

    private String email;

    private String password;

    private LocalDateTime createdAt;

    private String role;

    private List<PropertyResponseDto> propertiesOwned;

    private String mobileNumber;
}
