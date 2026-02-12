package com.project.vms.vmsproject.controller;

import com.project.vms.vmsproject.dto.cvedto.CveRequestDto;
import com.project.vms.vmsproject.dto.cvedto.CveResponseDto;
import com.project.vms.vmsproject.service.impl.VmsCveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CveController {

    private final VmsCveService cveService;

    @PostMapping("/cve/create")
    public ResponseEntity<CveResponseDto> createCve(@RequestBody CveRequestDto cveRequestDto){
        CveResponseDto cveResponseDto = cveService.createCve(cveRequestDto);
        return ResponseEntity.ok(cveResponseDto);
    }
    @GetMapping("/cve/{cveId}")
    public ResponseEntity<CveResponseDto> getCveByCveId(@PathVariable String cveId){
        CveResponseDto cveResponseDto = cveService.getCveByCveId(cveId);
        return ResponseEntity.ok(cveResponseDto);
    }
    @GetMapping("/cve")
    public ResponseEntity<List<CveResponseDto>> getAllCve(){
        List<CveResponseDto> response = cveService.getAllCves();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/cve/status")
    public ResponseEntity<List<CveResponseDto>> getCvesByStatus(@RequestParam String status){
        List<CveResponseDto> response = cveService.getAllCvesByStatus(status);
        if(response.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cve/severity")
    public ResponseEntity<List<CveResponseDto>> getCvesBySeverity(@RequestParam String severity) {
        List<CveResponseDto> response = cveService.getCveBySeverityMethod(severity);
        if (response.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cve/description")
    public ResponseEntity<List<CveResponseDto>> getCvesByDescription(@RequestParam String description) {
        List<CveResponseDto> response = cveService.getCveByDescriptionMethod(description);
        if (response.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/cve/cveId/{cveId}")
    public ResponseEntity<String> deleteCveByCveId(@PathVariable String cveId) {
        boolean isDeleted = cveService.deleteCveByCveIdMethod(cveId);
        if (isDeleted) {
            return ResponseEntity.ok("CVE deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/cve/{id}")
    public ResponseEntity<String> deleteCveById(@PathVariable Long id){
        boolean isDeleted = cveService.deleteCveByIdMethod(id);
        if (isDeleted) {
            return ResponseEntity.ok("CVE deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}