package com.project.vms.vmsproject.dto.userdto;

import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequestDto {
    @NotBlank(message = "Name is mandatory")
    public String name;
    @NotBlank(message = "Password is mandatory")
    public String password;
    @NotBlank(message = "Email is mandatory")
    @Email
    public String email;
    @NotBlank(message = "Phone number is mandatory")
    @Size(min = 10, max = 10, message = "Phone number must be 10 digits")
    public String phoneNumber;

    @Transient
    public String confirmPassword;

}
