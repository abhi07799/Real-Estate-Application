package com.real.estate.service;

import com.real.estate.dto.request.OwnerRequestDto;
import com.real.estate.dto.response.OwnerResponseDto;
import com.real.estate.dto.response.PropertyResponseDto;
import com.real.estate.exception.NoResourceAvailableException;
import com.real.estate.exception.ResourceAlreadyExistException;
import com.real.estate.model.OwnerModel;
import com.real.estate.model.PropertyModel;
import com.real.estate.repository.OwnerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService
{
    @Autowired
    private OwnerRepository ownerRepo;

    @Autowired
    private ModelMapper mapper;

    public OwnerResponseDto addOwner(OwnerRequestDto ownerRequestDto)
    {
        OwnerModel ownerModel = mapper.map(ownerRequestDto, OwnerModel.class);
        if (ownerRepo.existsByEmail(ownerModel.getEmail()))
        {
            throw new ResourceAlreadyExistException("Owner with this Email already exists");
        }
        return mapper.map(ownerRepo.save(ownerModel), OwnerResponseDto.class);
    }

    public List<OwnerResponseDto> getAllOwners()
    {
        List<OwnerModel> ownerModelList = ownerRepo.findAll();
        List<OwnerResponseDto> ownerResponseDtoList = ownerModelList.stream().map(ownerModel -> mapper.map(ownerModel, OwnerResponseDto.class)).toList();
        if (ownerResponseDtoList.isEmpty())
        {
            throw new NoResourceAvailableException("No owners found");
        }

        List<OwnerResponseDto> ownerResponseList = ownerModelList.stream().map(ownerModel -> {
                    OwnerResponseDto ownerResponseDto = mapper.map(ownerModel, OwnerResponseDto.class);
                    // Convert properties using the existing convertToDto method
                    List<PropertyResponseDto> properties = convertToDto(ownerModel.getPropertiesOwned());
                    ownerResponseDto.setPropertiesOwned(properties);
                    return ownerResponseDto;
                })
                .toList();
        return ownerResponseList;
    }

    public OwnerResponseDto getOwnerById(long ownerId)
    {
        Optional<OwnerModel> ownerModelOptional = ownerRepo.findById(ownerId);
        if (ownerModelOptional.isEmpty())
        {
            throw new NoResourceAvailableException("No owners found with id: " + ownerId);
        }
        OwnerModel ownerModel = ownerModelOptional.get();
        OwnerResponseDto response = mapper.map(ownerModel, OwnerResponseDto.class);

        List<PropertyResponseDto> list = convertToDto(ownerModel.getPropertiesOwned());

        response.setPropertiesOwned(list);
        return response;
    }

    public List<PropertyResponseDto> convertToDto(List<PropertyModel> list)
    {
        List<PropertyResponseDto> propertyResponseDtoList = list.stream().map(owner -> new PropertyResponseDto().builder()
                        .id(owner.getId())
                        .propertyTitle(owner.getPropertyTitle())
                        .propertyDescription(owner.getPropertyDescription())
                        .propertyAddress(owner.getPropertyAddress())
                        .propertyPrice(owner.getPropertyPrice())
                        .propertyType(owner.getPropertyType())
                        .propertyStatus(owner.getPropertyStatus())
                        .propertyApproval(owner.getPropertyApproval())
                        .propertyCreatedDate(owner.getPropertyCreatedDate())
                        .propertyUpdatedDate(owner.getPropertyUpdatedDate())
                        .purchasedByCustomer(owner.getPurchasedByCustomer())
                        .build())
                .toList();

        return propertyResponseDtoList;
    }
}
