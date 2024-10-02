package com.real.estate.controller;

import com.real.estate.dto.request.PropertyRequestDto;
import com.real.estate.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/listing/")
public class PropertyController
{
    @Autowired
    private PropertyService propertyService;

    @GetMapping("test")
    public String test()
    {
        return "Jai Bajrang Bali";
    }

    @PostMapping("addProperty")
    public ResponseEntity<?> addProperty(@RequestBody PropertyRequestDto propertyRequestDto)
    {
        return new ResponseEntity<>(propertyService.createProperty(propertyRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("getAllProperties")
    public ResponseEntity<?> getAllProperties()
    {
        return new ResponseEntity<>(propertyService.getAllProperties(), HttpStatus.OK);
    }

    @GetMapping("getProperty/{propertyId}")
    public ResponseEntity<?> getPropertyById(@PathVariable Long propertyId)
    {
        return new ResponseEntity<>(propertyService.getPropertyById(propertyId), HttpStatus.OK);
    }

    @GetMapping("searchProperty")
    public ResponseEntity<?> searchPropertyByTitle(@RequestParam String title)
    {
        return new ResponseEntity<>(propertyService.searchPropertyByTitle(title), HttpStatus.OK);
    }

    @PutMapping("admin/{adminId}/approveProperty/{propertyId}")
    public ResponseEntity<?> approvePropertyById(@PathVariable Long adminId, @PathVariable Long propertyId)
    {
        return new ResponseEntity<>(propertyService.approvePropertyById(adminId, propertyId), HttpStatus.OK);
    }

    @PutMapping("admin/{adminId}/rejectProperty/{propertyId}")
    public ResponseEntity<?> rejectPropertyById(@PathVariable Long adminId, @PathVariable Long propertyId)
    {
        return new ResponseEntity<>(propertyService.rejectPropertyById(adminId, propertyId), HttpStatus.OK);
    }

    @PutMapping("customer/{customerId}/purchaseProperty/{propertyId}")
    public ResponseEntity<?> purchaseProperty(@PathVariable Long customerId, @PathVariable Long propertyId)
    {
        return new ResponseEntity<>(propertyService.purchaseProperty(customerId, propertyId), HttpStatus.OK);
    }

    @DeleteMapping("removeProperty/{propertyId}")
    public ResponseEntity<?> removeProperty(@PathVariable Long propertyId)
    {
        return new ResponseEntity<>(propertyService.removeProperty(propertyId), HttpStatus.OK);
    }


}
