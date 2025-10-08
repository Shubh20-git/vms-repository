package com.project.vms.vmsproject.dto.productdto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
    @NotBlank(message = "Product name is mandatory")
    private String name;
    @NotBlank(message = "Product model number is mandatory")
    private String modelNo;
    @NotBlank(message = "This field is mandatory")
    private String publishedAt;
    @NotBlank(message = "Product url is mandatory")
    private String url;
    @NotBlank(message = "Product software version is mandatory")
    private String softwareVersion;
}
