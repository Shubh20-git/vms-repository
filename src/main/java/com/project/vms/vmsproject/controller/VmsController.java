package com.project.vms.vmsproject.controller;

import com.project.vms.vmsproject.dto.VmsRequestDto;
import com.project.vms.vmsproject.dto.VmsResponseDto;
import com.project.vms.vmsproject.service.impl.VmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VmsController {
    @Autowired
    private VmsUserService vmsUserService;

    @PostMapping("vms/admin/register")
    public VmsResponseDto registerAdmin(@RequestBody VmsRequestDto vmsRequestDto) {
        return vmsUserService.createAdmin(vmsRequestDto);
    }
    @PostMapping("vms/advisor/register")
    public VmsResponseDto registerAdvisor(@RequestBody VmsRequestDto vmsRequestDto) {
        return vmsUserService.createAdvisor(vmsRequestDto);
    }
}
