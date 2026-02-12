
package com.project.vms.vmsproject.service.impl;
import com.project.vms.vmsproject.dto.userdto.UserRegisterRequestDto;
import com.project.vms.vmsproject.dto.userdto.UserRegisterResponseDto;
import com.project.vms.vmsproject.enums.UserRole;
import com.project.vms.vmsproject.exceptions.CustomExceptions;
import com.project.vms.vmsproject.model.VmsUser;
import com.project.vms.vmsproject.repository.UserRepository;
import com.project.vms.vmsproject.service.UserServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VmsUserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;
    
    public UserRegisterResponseDto createAdmin(UserRegisterRequestDto userRegisterRequestDto) {
        VmsUser vmsUser = modelMapper.map(userRegisterRequestDto, VmsUser.class);
        vmsUser.setRole(UserRole.ADMIN);
        return modelMapper.map(userRepository.save(vmsUser), UserRegisterResponseDto.class);
    }

    public UserRegisterResponseDto createAdvisor(UserRegisterRequestDto userRegisterRequestDto) {
        VmsUser vmsUser = modelMapper.map(userRegisterRequestDto, VmsUser.class);
        vmsUser.setRole(UserRole.ADVISOR);
        return modelMapper.map(userRepository.save(vmsUser), UserRegisterResponseDto.class);
    }

    public String loginUser(String email, String password) {
        Optional<VmsUser> user = userRepository.findByEmailAndPassword(email, password);
        if (user.isEmpty()) {
            throw new CustomExceptions.InvalidCredentialsException("Invalid email or password");
        }
        VmsUser vmsUser = user.get();
        return "Login successful! Welcome " + vmsUser.getName() + " (" + vmsUser.getRole() + ")";

    }

    public List<UserRegisterResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserRegisterResponseDto.class))
                .toList();
    }

    public Optional<UserRegisterResponseDto> getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> modelMapper.map(user, UserRegisterResponseDto.class));
    }


    public List<UserRegisterResponseDto> getUserByName(String name) {
        List<VmsUser> employees = userRepository.findByName(name);
        List<UserRegisterResponseDto> responseList = new ArrayList<>();
        for (VmsUser v : employees) {
            UserRegisterResponseDto employeeResponseDto = modelMapper.map(v, UserRegisterResponseDto.class);
            responseList.add(employeeResponseDto);
        }
        return responseList;
    }


    public UserRegisterResponseDto patchUpdateUser(Long id, UserRegisterRequestDto userRegisterRequestDto) {
        VmsUser vmsUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (userRegisterRequestDto.getName() != null) vmsUser.setName(userRegisterRequestDto.getName());
        if (userRegisterRequestDto.getPassword() != null) vmsUser.setPassword(userRegisterRequestDto.getPassword());
        if (userRegisterRequestDto.getEmail() != null) vmsUser.setEmail(userRegisterRequestDto.getEmail());
        if (userRegisterRequestDto.getPhoneNumber() != null)
            vmsUser.setPhoneNumber(userRegisterRequestDto.getPhoneNumber());

        return modelMapper.map(userRepository.save(vmsUser), UserRegisterResponseDto.class);
    }

    public String deleteUserById(Long id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return "User deleted successfully";
        }).orElse("User not found");
    }

    public String deleteAllUsers() {
        userRepository.deleteAll();
        return "All users deleted successfully";
    }

    public List<UserRegisterResponseDto> getUserByNameMethod(String name) {
        List<VmsUser> employees = userRepository.findByName(name);
        List<UserRegisterResponseDto> responseList = new ArrayList<>();
        for (VmsUser emp : employees) {
            UserRegisterResponseDto employeeResponseDto = modelMapper.map(emp, UserRegisterResponseDto.class);
            responseList.add(employeeResponseDto);
        }
        return responseList;
    }

    public UserRegisterResponseDto patchUserMethod(Long id, UserRegisterRequestDto dto) {
        Optional<VmsUser> userOpt = userRepository.findById(id);
        VmsUser vmsUser;
        if (userOpt.isPresent()) {
            vmsUser = userOpt.get();
        } else {
            throw new CustomExceptions.UserNotFoundException("User not found with id: " + id);
        }
        if (dto.getName() != null) vmsUser.setName(dto.getName());
        if (dto.getEmail() != null) vmsUser.setEmail(dto.getEmail());
        if (dto.getPassword() != null) vmsUser.setPassword(dto.getPassword());

        VmsUser updated = userRepository.save(vmsUser);
        return modelMapper.map(updated, UserRegisterResponseDto.class);
    }
}
