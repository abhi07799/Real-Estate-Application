package com.real.estate.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.real.estate.model.AdminModel;
import com.real.estate.model.CustomerModel;
import com.real.estate.model.OwnerModel;
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
public class PropertyResponseDto
{
    private long id;

    private String propertyTitle;

    private String propertyDescription;

    private String propertyPrice;

    private String propertyAddress;

    private String propertyType;

    private String propertyApproval;

    private String propertyStatus;

    private OwnerModel owner;

    private CustomerModel purchasedByCustomer;

    private AdminModel approvedByAdmin;

    private LocalDateTime propertyCreatedDate;

    private LocalDateTime propertyUpdatedDate;
}
