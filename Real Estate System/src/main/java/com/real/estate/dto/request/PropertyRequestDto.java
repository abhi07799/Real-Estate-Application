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
    @NotEmpty
    @NotNull
    private String propertyTitle;

    @NotEmpty
    private String propertyDescription;

    @NotEmpty
    private String propertyPrice;

    @NotEmpty
    private String propertyAddress;

    @NotEmpty
    private String propertyType;

    @NotEmpty
    private OwnerModel owner;

}
