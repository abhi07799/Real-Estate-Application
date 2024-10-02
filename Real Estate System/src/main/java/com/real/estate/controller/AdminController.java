package com.real.estate.controller;

import com.real.estate.dto.request.AdminRequestDto;
import com.real.estate.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/admin/")
public class AdminController
{
    @Autowired
    private AdminService adminService;

    @PostMapping("addAdmin")
    public ResponseEntity<?> addAdmin(@Valid @RequestBody AdminRequestDto adminRequestDto)
    {
        return new ResponseEntity<>(adminService.addAdmin(adminRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("getAdmin/{adminId}")
    public ResponseEntity<?> getAdminById(@PathVariable long adminId)
    {
        return new ResponseEntity<>(adminService.getAdminById(adminId), HttpStatus.OK);
    }

    @GetMapping("getAllAdmin")
    public ResponseEntity<?> getAllAdmins()
    {
        return new ResponseEntity<>(adminService.getAllAdmins(), HttpStatus.OK);
    }
}
