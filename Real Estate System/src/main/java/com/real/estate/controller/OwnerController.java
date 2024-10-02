package com.real.estate.controller;

import com.real.estate.dto.request.OwnerRequestDto;
import com.real.estate.service.OwnerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/owner/")
public class OwnerController
{
    @Autowired
    private OwnerService ownerService;

    @PostMapping("addOwner")
    public ResponseEntity<?> addOwner(@Valid @RequestBody OwnerRequestDto ownerRequestDto)
    {
        return new ResponseEntity<>(ownerService.addOwner(ownerRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("getOwner/{ownerId}")
    public ResponseEntity<?> getOwnerById(@PathVariable long ownerId)
    {
        return new ResponseEntity<>(ownerService.getOwnerById(ownerId), HttpStatus.OK);
    }

    @GetMapping("getAllOwner")
    public ResponseEntity<?> getAllOwners()
    {
        return new ResponseEntity<>(ownerService.getAllOwners(), HttpStatus.OK);
    }
}
