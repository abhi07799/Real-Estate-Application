package com.real.estate.dto.request;

import com.real.estate.model.AdminModel;
import com.real.estate.model.CustomerModel;
import com.real.estate.model.OwnerModel;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyRequestDto
{
    @NotEmpty(message = "Title should be empty")
    @NotNull
    private String propertyTitle;

    @NotEmpty(message = "description should not be empty")
    private String propertyDescription;

    @NotEmpty(message = "price should not be empty")
    private String propertyPrice;

    @NotEmpty(message = "address should not be empty")
    private String propertyAddress;

    @NotEmpty(message = "property type should not be empty")
    private String propertyType;

    @NotEmpty(message = "owner should not be empty")
    private OwnerModel owner;

}
