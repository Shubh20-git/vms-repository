package com.project.vms.vmsproject.dto.productdto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {
    private String name;
    private String modelNo;
    private String publishedAt;
    private String url;
    private String softwareVersion;
}
