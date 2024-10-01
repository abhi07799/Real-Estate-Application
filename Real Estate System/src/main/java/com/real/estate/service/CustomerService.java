package com.real.estate.service;

import com.real.estate.dto.request.CustomerRequestDto;
import com.real.estate.dto.response.CustomerResponseDto;
import com.real.estate.exception.NoResourceFoundException;
import com.real.estate.model.CustomerModel;
import com.real.estate.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService
{
    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private ModelMapper mapper;

    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto)
    {
        CustomerModel customerModel = mapper.map(customerRequestDto, CustomerModel.class);
        return mapper.map(customerRepo.save(customerModel), CustomerResponseDto.class);
    }

    public List<CustomerResponseDto> getAllCustomers()
    {
        List<CustomerModel> customerModelList = customerRepo.findAll();
        List<CustomerResponseDto> customerResponseDtoList = customerModelList.stream().map(customerModel -> mapper.map(customerModel, CustomerResponseDto.class)).toList();
        if(customerResponseDtoList.isEmpty())
        {
            throw new NoResourceFoundException("No customers found");
        }
        return customerResponseDtoList;
    }

    public CustomerResponseDto getCustomerById(long customerId)
    {
        Optional<CustomerModel> customerModel = customerRepo.findById(customerId);
        if(customerModel.isEmpty())
        {
            throw new NoResourceFoundException("No customers found with id: " + customerId);
        }
        return mapper.map(customerModel.get(), CustomerResponseDto.class);
    }
}
