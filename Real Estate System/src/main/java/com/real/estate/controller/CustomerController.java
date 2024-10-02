package com.real.estate.controller;

import com.real.estate.dto.request.CustomerRequestDto;
import com.real.estate.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer/")
public class CustomerController
{
    @Autowired
    private CustomerService customerService;

    @GetMapping("test")
    public String test()
    {
        return "Jai Bajrang Bali";
    }

    @PostMapping("addCustomer")
    public ResponseEntity<?> addCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto)
    {
        return new ResponseEntity<>(customerService.addCustomer(customerRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("getCustomer/{customerId}")
    public ResponseEntity<?> getCustomerById(@PathVariable long customerId)
    {
        return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }

    @GetMapping("getAllCustomer")
    public ResponseEntity<?> getAllCustomers()
    {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }
}
