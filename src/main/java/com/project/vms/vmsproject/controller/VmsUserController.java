package com.project.vms.vmsproject.controller;

import com.project.vms.vmsproject.dto.logindto.UserLoginRequestDto;
import com.project.vms.vmsproject.dto.userdto.UserRegisterRequestDto;
import com.project.vms.vmsproject.dto.userdto.UserRegisterResponseDto;
import com.project.vms.vmsproject.service.impl.VmsUserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/api/v1/users")
public class VmsUserController {

    @Autowired
    private VmsUserService vmsUserService;

    // Register Admin
    @PostMapping("/register/admin")
    public ResponseEntity<UserRegisterResponseDto> registerAdmin(@Valid @RequestBody UserRegisterRequestDto dto) {
        return ResponseEntity.ok(vmsUserService.createAdmin(dto));
    }

    // Register Advisor
    @PostMapping("/register/advisor")
    public ResponseEntity<UserRegisterResponseDto> registerAdvisor(@Valid @RequestBody UserRegisterRequestDto dto) {
        return ResponseEntity.ok(vmsUserService.createAdvisor(dto));
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody UserLoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(vmsUserService.loginUser(loginRequestDto.getEmail(), loginRequestDto.getPassword()));
    }

    // Get All Users
    @GetMapping
    public ResponseEntity<List<UserRegisterResponseDto>> getAllUsers() {
        return ResponseEntity.ok(vmsUserService.getAllUsers());
    }

    // Get User By ID
    @GetMapping("/{id}")
    public ResponseEntity<UserRegisterResponseDto> getUserById(@PathVariable @NotNull(message = "ID cannot be null") Long id) {
        Optional<UserRegisterResponseDto> user = vmsUserService.getUserById(id);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Search User By Name
    @GetMapping("user/search")
    public ResponseEntity<List<UserRegisterResponseDto>> getUserByName(@RequestParam String name) {
        List<UserRegisterResponseDto> employees = vmsUserService.getUserByNameMethod(name);
        if(employees.isEmpty()){
            return ResponseEntity.status(404).body(employees);
        }
        return ResponseEntity.ok(employees);
    }

    // Update User (Patch)
    @PatchMapping("z/{id}")
    public ResponseEntity<UserRegisterResponseDto> patchUser(
            @PathVariable Long id,
            @RequestBody UserRegisterRequestDto dto) {
        UserRegisterResponseDto updatedEmployee = vmsUserService.patchUserMethod(id, dto);
        if(updatedEmployee!=null){
            return ResponseEntity.ok(updatedEmployee);
        }
        return ResponseEntity.notFound().build();
    }


    // Delete User By ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable @NotNull(message = "ID cannot be empty") Long id) {
        return ResponseEntity.ok(vmsUserService.deleteUserById(id));
    }

    // Delete All Users
    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAllUsers() {
        return ResponseEntity.ok(vmsUserService.deleteAllUsers());
    }
}
