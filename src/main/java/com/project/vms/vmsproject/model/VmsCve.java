package com.project.vms.vmsproject.model;

import com.project.vms.vmsproject.enums.CveStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class VmsCve {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String createdAt;
    @Column(unique=true)
    private String cveId;
    private String updatedAt;
    @Enumerated(EnumType.STRING)
    private CveStatus status;

    @Enumerated(EnumType.STRING)
    private String severity;

    @OneToMany
    private Product product;

}
