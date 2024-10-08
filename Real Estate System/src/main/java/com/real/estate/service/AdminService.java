package com.real.estate.service;

import com.real.estate.dto.request.AdminRequestDto;
import com.real.estate.dto.response.AdminResponseDto;
import com.real.estate.exception.NoResourceAvailableException;
import com.real.estate.exception.ResourceAlreadyExistException;
import com.real.estate.model.AdminModel;
import com.real.estate.repository.AdminRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService
{
    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private ModelMapper mapper;

    public AdminResponseDto addAdmin(AdminRequestDto adminRequestDto)
    {
        AdminModel adminModel = mapper.map(adminRequestDto, AdminModel.class);
        if(adminRepo.existsByEmail(adminModel.getEmail()))
        {
            throw new ResourceAlreadyExistException("Admin with this Email already exists");
        }
        return mapper.map(adminRepo.save(adminModel), AdminResponseDto.class);
    }

    public List<AdminResponseDto> getAllAdmins()
    {
        List<AdminModel> adminModelList = adminRepo.findAll();
        List<AdminResponseDto> adminResponseDtoList = adminModelList.stream().map(adminModel -> mapper.map(adminModel, AdminResponseDto.class)).toList();
        if(adminResponseDtoList.isEmpty())
        {
            throw new NoResourceAvailableException("No admin found");
        }
        return adminResponseDtoList;
    }

    public AdminResponseDto getAdminById(long adminId)
    {
        Optional<AdminModel> adminModel = adminRepo.findById(adminId);
        if(adminModel.isEmpty())
        {
            throw new NoResourceAvailableException("No admin found with id: " + adminId);
        }
        return mapper.map(adminModel.get(), AdminResponseDto.class);
    }
}
