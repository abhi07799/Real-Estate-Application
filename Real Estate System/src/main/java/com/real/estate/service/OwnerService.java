package com.real.estate.service;

import com.real.estate.dto.request.OwnerRequestDto;
import com.real.estate.dto.response.OwnerResponseDto;
import com.real.estate.exception.NoResourceAvailableException;
import com.real.estate.exception.ResourceAlreadyExistException;
import com.real.estate.model.OwnerModel;
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
        if(ownerRepo.existsByEmail(ownerModel.getEmail()))
        {
            throw new ResourceAlreadyExistException("Owner with this Email already exists");
        }
        return mapper.map(ownerRepo.save(ownerModel), OwnerResponseDto.class);
    }

    public List<OwnerResponseDto> getAllOwners()
    {
        List<OwnerModel> ownerModelList = ownerRepo.findAll();
        List<OwnerResponseDto> ownerResponseDtoList = ownerModelList.stream().map(ownerModel -> mapper.map(ownerModel, OwnerResponseDto.class)).toList();
        if(ownerResponseDtoList.isEmpty())
        {
            throw new NoResourceAvailableException("No owners found");
        }
        return ownerResponseDtoList;
    }

    public OwnerResponseDto getOwnerById(long ownerId)
    {
        Optional<OwnerModel> ownerModel = ownerRepo.findById(ownerId);
        if(ownerModel.isEmpty())
        {
            throw new NoResourceAvailableException("No owners found with id: " + ownerId);
        }
        return mapper.map(ownerModel.get(), OwnerResponseDto.class);
    }
}
