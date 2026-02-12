package com.project.vms.vmsproject.service.impl;

import com.project.vms.vmsproject.dto.cvedto.CveRequestDto;
import com.project.vms.vmsproject.dto.cvedto.CveResponseDto;
import com.project.vms.vmsproject.dto.productdto.ProductResponseDto;
import com.project.vms.vmsproject.enums.CveSeverity;
import com.project.vms.vmsproject.enums.CveStatus;
import com.project.vms.vmsproject.exceptions.CustomExceptions;
import com.project.vms.vmsproject.model.Product;
import com.project.vms.vmsproject.model.VmsCve;
import com.project.vms.vmsproject.repository.CveRepository;
import com.project.vms.vmsproject.repository.ProductRepository;
import com.project.vms.vmsproject.util.VmsUtility;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class VmsCveService {
    private final CveRepository cveRepository;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepo;

    public CveResponseDto createCve(CveRequestDto cveRequestDto) {
        UUID uuid = UUID.randomUUID();
        String uuId = uuid.toString();
        Product product = productRepo.findById(cveRequestDto.getProductId())
                .orElseThrow(() -> new CustomExceptions.ProductNotFoundException("Product not found"));
        VmsCve cve = modelMapper.map(cveRequestDto, VmsCve.class);
        cve.setCveId("cve-"+uuId);
        cve.setId(null);
        cve.setCreatedAt(Instant.now().toString());
        cve.setUpdatedAt(Instant.now().toString());
        VmsCve result = cveRepository.save(cve);
        CveResponseDto cveResponseDto = modelMapper.map(result,CveResponseDto.class);
        cveResponseDto.setProductResponseDto(modelMapper.map(product, ProductResponseDto.class));
        return cveResponseDto;
    }

    public CveResponseDto getCveByCveId(String cveId) {
        Optional<VmsCve> cve = cveRepository.findByCveId(cveId);
        if(cve.isEmpty()){
            throw new CustomExceptions.CveNotFoundException("CVE not found");
        }
        CveResponseDto cveResponseDto = modelMapper.map(cve,CveResponseDto.class);
        cveResponseDto.setProductResponseDto(modelMapper.map(cve.get(),ProductResponseDto.class));
        return cveResponseDto;
    }

    public List<CveResponseDto> getAllCves() {
        List<VmsCve> cve = cveRepository.findAll();
        return cve.stream()
                .map((value)->(
                        modelMapper.map(value,CveResponseDto.class))
                )
                .toList();
    }

    public List<CveResponseDto> getAllCvesByStatus(String status) {
        CveStatus cveStatus = VmsUtility.cveStatusFromString(status);
        List<VmsCve> cve = cveRepository.findByCveStatus(cveStatus);
        List<CveResponseDto> responseList = new ArrayList<>();
        for(VmsCve cveItem : cve){
            CveResponseDto cveResponseDto = modelMapper.map(cveItem,CveResponseDto.class);
            cveResponseDto.setProductResponseDto(modelMapper.map(cveItem.getProduct(), ProductResponseDto.class));
            responseList.add(cveResponseDto);
        }
        return responseList;
    }

    public List<CveResponseDto> getCveBySeverityMethod(String severity) {
        CveSeverity sv = VmsUtility.severityFromString(severity);
            List<VmsCve> cve = cveRepository.findBySeverity(sv);
            List<CveResponseDto> listCve = new ArrayList<>();
            for(VmsCve cveItem : cve) {
                listCve.add(modelMapper.map(cveItem, CveResponseDto.class));
            }
            return listCve;

    }

    public List<CveResponseDto> getCveByDescriptionMethod(String description) {
        List<VmsCve> cve = cveRepository.findByDescriptionContainingIgnoreCase(description);
        List<CveResponseDto> listCve = new ArrayList<>();
        for(VmsCve cveItem : cve) {
            CveResponseDto cveDto = modelMapper.map(cveItem, CveResponseDto.class);
            cveDto.setProductResponseDto(modelMapper.map(cveItem.getProduct(), ProductResponseDto.class));
            listCve.add(cveDto);
        }
        return listCve;
    }
    }

