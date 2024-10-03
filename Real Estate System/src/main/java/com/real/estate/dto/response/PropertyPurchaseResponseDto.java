package com.real.estate.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PropertyPurchaseResponseDto
{
    private long id;

    private String propertyTitle;

    private String propertyDescription;

    private String propertyPrice;

    private String propertyAddress;

    private String propertyType;

    private String propertyApproval;

    private String propertyStatus;

    private CustomerResponseDto ownerDetails;

    private LocalDateTime propertyCreatedDate;

    private LocalDateTime propertyUpdatedDate;
}
