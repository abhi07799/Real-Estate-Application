package com.real.estate.service;

import com.real.estate.dto.request.PropertyRequestDto;
import com.real.estate.dto.response.PropertyResponseDto;
import com.real.estate.exception.NoResourceAvailableException;
import com.real.estate.model.AdminModel;
import com.real.estate.model.CustomerModel;
import com.real.estate.model.OwnerModel;
import com.real.estate.model.PropertyModel;
import com.real.estate.repository.AdminRepository;
import com.real.estate.repository.CustomerRepository;
import com.real.estate.repository.OwnerRepository;
import com.real.estate.repository.PropertyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService
{
    @Autowired
    private PropertyRepository propertyRepo;

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private OwnerRepository ownerRepo;

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private ModelMapper mapper;

    //owner
    public PropertyResponseDto createProperty(PropertyRequestDto propertyRequestDto)
    {
        Optional<OwnerModel> ownerOptional = ownerRepo.findById(propertyRequestDto.getOwner().getId());
        if (ownerOptional.isEmpty())
        {
            throw new NoResourceAvailableException("Owner not found for id " + propertyRequestDto.getOwner().getId());
        }

        PropertyModel model = mapper.map(propertyRequestDto, PropertyModel.class);
        model.setPropertyStatus("Need Approval");
        model.setPropertyApproval("Queued");
        return mapper.map(propertyRepo.save(model), PropertyResponseDto.class);
    }

    //admin
    public List<PropertyResponseDto> getAllProperties()
    {
        List<PropertyModel> models = propertyRepo.findAll();
        if (models.isEmpty())
        {
            throw new NoResourceAvailableException("No Properties Found");
        }

        return models.stream().map(response -> mapper.map(response, PropertyResponseDto.class)).toList();
    }

    //owner admin user
    public PropertyResponseDto getPropertyById(long propertyId)
    {
        Optional<PropertyModel> property = propertyRepo.findById(propertyId);

        if (property.isEmpty())
        {
            throw new NoResourceAvailableException("No Property Found with ID: " + propertyId);
        }
        return mapper.map(property.get(), PropertyResponseDto.class);
    }

    //user
    public List<PropertyResponseDto> searchPropertyByTitle(String propertyTitle)
    {
        List<PropertyModel> propertyList = propertyRepo.findByPropertyTitleContainingIgnoreCase(propertyTitle);
        if (propertyList.isEmpty())
        {
            throw new NoResourceAvailableException("No Property Found with Title: " + propertyTitle);
        }
        return propertyList.stream().map(response -> mapper.map(response, PropertyResponseDto.class)).toList();
    }

    //admin
    public PropertyResponseDto approvePropertyById(long adminId, long propertyId)
    {
        Optional<PropertyModel> property = propertyRepo.findById(propertyId);
        if (property.isEmpty())
        {
            throw new NoResourceAvailableException("No Property Found with ID: " + propertyId);
        }
        Optional<AdminModel> admin = adminRepo.findById(adminId);
        if (admin.isEmpty())
        {
            throw new NoResourceAvailableException("No Admin Found with ID: " + adminId);
        }
        PropertyModel propertyModel = mapper.map(property.get(), PropertyModel.class);
        propertyModel.setPropertyApproval("Approved");
        propertyModel.setApprovedByAdmin(admin.get());
        return mapper.map(propertyRepo.save(propertyModel), PropertyResponseDto.class);
    }

    //admin
    public PropertyResponseDto rejectPropertyById(long adminId, long propertyId)
    {
        Optional<AdminModel> admin = adminRepo.findById(adminId);
        if (admin.isEmpty())
        {
            throw new NoResourceAvailableException("No Admin Found with ID: " + adminId);
        }
        Optional<PropertyModel> property = propertyRepo.findById(propertyId);
        if (property.isEmpty())
        {
            throw new NoResourceAvailableException("No Property Found with ID: " + propertyId);
        }

        PropertyModel propertyModel = mapper.map(property.get(), PropertyModel.class);
        propertyModel.setPropertyApproval("Reject");
        propertyModel.setApprovedByAdmin(admin.get());
        return mapper.map(propertyRepo.save(propertyModel), PropertyResponseDto.class);
    }

    //user
    public PropertyResponseDto purchaseProperty(long customerId, long propertyId)
    {
        Optional<PropertyModel> property = propertyRepo.findById(propertyId);
        if (property.isEmpty())
        {
            throw new NoResourceAvailableException("No Property Found with ID: " + propertyId);
        }
        Optional<CustomerModel> customer = customerRepo.findById(customerId);
        if (customer.isEmpty())
        {
            throw new NoResourceAvailableException("No Customer Found with ID: " + customerId);
        }
        PropertyModel propertyModel = mapper.map(customer.get(), PropertyModel.class);
        propertyModel.setPropertyStatus("Sold");
        propertyModel.setPurchasedByCustomer(customer.get());
        return mapper.map(propertyRepo.save(propertyModel), PropertyResponseDto.class);
    }

    //owner
    public PropertyResponseDto removeProperty(long propertyId)
    {
        Optional<PropertyModel> property = propertyRepo.findById(propertyId);
        if (property.isEmpty())
        {
            throw new NoResourceAvailableException("No Property Found with ID: " + propertyId);
        }

        PropertyModel propertyModel = property.get();
        propertyModel.setPropertyStatus("InActive");
        return mapper.map(propertyRepo.save(propertyModel), PropertyResponseDto.class);
    }
}
