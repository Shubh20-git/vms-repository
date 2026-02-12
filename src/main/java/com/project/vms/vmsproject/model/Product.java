package com.project.vms.vmsproject.model;

import com.project.vms.vmsproject.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String modelNumber;
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;
    private LocalDateTime publishedAt;
    private String url;

    // One Product can have many CVEs
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VmsCve> cves = new ArrayList<>();
}
