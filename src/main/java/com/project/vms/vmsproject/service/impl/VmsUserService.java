package com.project.vms.vmsproject.service.impl;

import com.project.vms.vmsproject.dto.VmsRequestDto;
import com.project.vms.vmsproject.dto.VmsResponseDto;
import com.project.vms.vmsproject.enums.UserRole;
import com.project.vms.vmsproject.model.VmsUser;
import com.project.vms.vmsproject.repository.VmsRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VmsUserService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private VmsRepo vmsRepo;

    public VmsResponseDto createAdmin(VmsRequestDto vmsRequestDto) {
        VmsUser vmsUser = modelMapper.map(vmsRequestDto, VmsUser.class);
        vmsUser.setRole(UserRole.ADMIN);
        VmsUser savedUser = vmsRepo.save(vmsUser);
        return modelMapper.map(savedUser, VmsResponseDto.class);
    }

    public VmsResponseDto createAdvisor(VmsRequestDto vmsRequestDto) {
        VmsUser vmsUser = modelMapper.map(vmsRequestDto, VmsUser.class);
        vmsUser.setRole(UserRole.ADVISOR);
        VmsUser savedUser = vmsRepo.save(vmsUser);
        return modelMapper.map(savedUser, VmsResponseDto.class);

    }
}
