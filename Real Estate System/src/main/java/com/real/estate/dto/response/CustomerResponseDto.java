package com.real.estate.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.real.estate.model.PropertyModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerResponseDto
{
    private long id;

    private String fullName;

    private String email;

    private String password;

    private LocalDateTime createdAt;

    private String role;

    private List<PropertyModel> purchasedProperties;
}
