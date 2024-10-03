package com.real.estate.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDto
{
    @NotEmpty(message = "Full Name can not be empty")
    @Length(min = 3, max = 20)
    private String fullName;

    @NotEmpty(message = "E-Mail can not be empty")
    private String email;

    @NotEmpty(message = "Password can not be empty")
    private String password;

    @NotEmpty(message = "Role can not be empty ")
    @Pattern(regexp = "ADMIN|OWNER|CUSTOMER|admin|owner|customer", message = "Role must be either ADMIN, OWNER, or CUSTOMER (No Case Sensitive)")
    private String role;

    private String mobileNumber;
}
