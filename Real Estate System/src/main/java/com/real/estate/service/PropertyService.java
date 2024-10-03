package com.real.estate.service;

import com.real.estate.dto.request.PropertyRequestDto;
import com.real.estate.dto.response.CustomerResponseDto;
import com.real.estate.dto.response.OwnerResponseDto;
import com.real.estate.dto.response.PropertyPurchaseResponseDto;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        List<PropertyResponseDto> responseDtoList = models.stream().map(model ->
                {
                    OwnerModel ownerModel = model.getOwner();
                    PropertyResponseDto propertyResponseDto = mapper.map(model, PropertyResponseDto.class);
                    OwnerResponseDto ownerResponse = convertToDto(ownerModel);
                    propertyResponseDto.setOwner(ownerResponse);
                    return propertyResponseDto;
                })
                .toList();
        return responseDtoList;
    }

    //owner admin user
    public PropertyResponseDto getPropertyById(long propertyId)
    {
        Optional<PropertyModel> property = propertyRepo.findById(propertyId);

        if (property.isEmpty())
        {
            throw new NoResourceAvailableException("No Property Found with ID: " + propertyId);
        }

        PropertyResponseDto response = mapper.map(property.get(), PropertyResponseDto.class);
        OwnerResponseDto ownerResponse = convertToDto(property.get().getOwner());
        response.setOwner(ownerResponse);
        return response;
    }

    //user
    public List<PropertyResponseDto> searchPropertyByTitle(String propertyTitle)
    {
        List<PropertyModel> propertyList = propertyRepo.findByPropertyTitleContainingIgnoreCase(propertyTitle);
        if (propertyList.isEmpty())
        {
            throw new NoResourceAvailableException("No Property Found with Title: " + propertyTitle);
        }
        List<PropertyResponseDto> responseDtoList = propertyList.stream().map(model ->
                {
                    OwnerModel ownerModel = model.getOwner();
                    PropertyResponseDto propertyResponseDto = mapper.map(model, PropertyResponseDto.class);
                    OwnerResponseDto ownerResponse = convertToDto(ownerModel);
                    propertyResponseDto.setOwner(ownerResponse);
                    return propertyResponseDto;
                })
                .toList();
        return responseDtoList;
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
        PropertyModel propertyModel = property.get();
        propertyModel.setPropertyApproval("Approved");
        propertyModel.setPropertyStatus("Active");
        propertyModel.setApprovedByAdmin(changeAdminDetails(admin.get()));
        PropertyResponseDto response = mapper.map(propertyRepo.save(propertyModel), PropertyResponseDto.class);
        OwnerResponseDto ownerResponse = convertToDto(property.get().getOwner());
        response.setOwner(ownerResponse);
        return response;
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

        PropertyModel propertyModel = property.get();
        propertyModel.setPropertyApproval("Reject");
        propertyModel.setPropertyStatus("InActive");
        propertyModel.setApprovedByAdmin(changeAdminDetails(admin.get()));
        PropertyResponseDto response = mapper.map(propertyRepo.save(propertyModel), PropertyResponseDto.class);
        OwnerResponseDto ownerResponse = convertToDto(property.get().getOwner());
        response.setOwner(ownerResponse);
        return response;
    }

    //user
    public PropertyPurchaseResponseDto purchaseProperty(long customerId, long propertyId)
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
        PropertyModel propertyModel = property.get();
        propertyModel.setPropertyStatus("Sold");
//        propertyModel.setPurchasedByCustomer(customer.get());
        PropertyResponseDto propertyResponse = mapper.map(propertyRepo.save(propertyModel), PropertyResponseDto.class);
        PropertyPurchaseResponseDto purchaseResponseDto = mapper.map(propertyResponse, PropertyPurchaseResponseDto.class);
        purchaseResponseDto.setOwnerDetails(convertToCustomerDto(customer.get()));

        return purchaseResponseDto;
    }

    //owner
    public Map<String, String> removeProperty(long propertyId)
    {
        Optional<PropertyModel> property = propertyRepo.findById(propertyId);
        if (property.isEmpty())
        {
            throw new NoResourceAvailableException("No Property Found with ID: " + propertyId);
        }

        PropertyModel propertyModel = property.get();
        propertyModel.setPropertyStatus("InActive");
        PropertyModel propertySaved = propertyRepo.save(propertyModel);
        Map<String, String> map = new HashMap<>();
        map.put("Message  ","Property { "+propertySaved.getPropertyTitle()+" } has been removed Successfully");
        return map;
    }

    public OwnerResponseDto convertToDto(OwnerModel ownerModel)
    {
        return new OwnerResponseDto().builder().id(ownerModel.getId()).fullName(ownerModel.getFullName()).email(ownerModel.getEmail()).mobileNumber(ownerModel.getMobileNumber()).build();
    }

    public CustomerResponseDto convertToCustomerDto(CustomerModel customerModel)
    {
        return new CustomerResponseDto().builder().id(customerModel.getId()).fullName(customerModel.getFullName()).email(customerModel.getEmail()).mobileNumber(customerModel.getMobileNumber()).build();
    }

    public AdminModel changeAdminDetails(AdminModel adminModel)
    {
        AdminModel admin = new AdminModel();
        admin.setId(adminModel.getId());
        admin.setFullName(adminModel.getFullName());
        admin.setEmail(adminModel.getEmail());
        admin.setMobileNumber(adminModel.getMobileNumber());
        return admin;
    }
}
