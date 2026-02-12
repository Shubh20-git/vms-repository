package com.project.vms.vmsproject.model;

import com.project.vms.vmsproject.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VmsUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;

    @Column(nullable = false,unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;
    private String phoneNumber;
    private boolean isVerified = false;
    @Transient
    private String confirmPassword;

}
