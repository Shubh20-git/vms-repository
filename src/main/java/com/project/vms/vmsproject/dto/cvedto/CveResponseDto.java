package com.project.vms.vmsproject.dto.cvedto;

import com.project.vms.vmsproject.dto.productdto.ProductResponseDto;
import com.project.vms.vmsproject.enums.CveSeverity;
import com.project.vms.vmsproject.enums.CveStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CveResponseDto {
    private Long id;
    private String cveId;
    private String description;
    private CveSeverity severity;
    private CveStatus status;
    private String versionStart;
    private String versionEnd;
    private String createdAt;
    private String updatedAt;
    private ProductResponseDto productResponseDto;
}