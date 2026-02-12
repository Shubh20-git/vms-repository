package com.project.vms.vmsproject.dto.cvedto;

import com.project.vms.vmsproject.version.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CveRequestDto {
    @NotBlank(message = "CVE description is mandatory")
    private String description;
    @NotNull(message = "CVE severity is mandatory")
    private String severity;
    @NotNull(message = "CVE status is mandatory")
    private String status;
    @NotBlank(message = "CVE version start is mandatory")
    private String versionStart;
    @NotBlank(message = "CVE version end is mandatory")
    private String versionEnd;
    @NotBlank(message = "CVE product id is mandatory")
    private Long productId;
    @NotBlank(message = "Version field cannot be empty")
    private Version version;
}
