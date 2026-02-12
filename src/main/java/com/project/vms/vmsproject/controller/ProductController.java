package com.project.vms.vmsproject.controller;

import com.project.vms.vmsproject.dto.productdto.ProductRequestDto;
import com.project.vms.vmsproject.dto.productdto.ProductResponseDto;
import com.project.vms.vmsproject.service.impl.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/products/create")
    public ResponseEntity<ProductResponseDto> createProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
        ProductResponseDto pRD = productService.createProduct(productRequestDto);
        return ResponseEntity.status(200).body(pRD);
    }
    @DeleteMapping("/products/deleteAll")
    public ResponseEntity<String> deleteAllProducts() {
        String message = productService.deleteAllProducts();
        return ResponseEntity.ok(message);
    }
    @GetMapping("/products/all")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts(){
        List<ProductResponseDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
    @GetMapping("/products")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
        ProductResponseDto products = productService.getProductsById(id);
        return ResponseEntity.ok(products);
    }
    @DeleteMapping("products/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id) {
        boolean isDeleted = productService.deleteById(id);
        if (isDeleted) {
            return ResponseEntity.ok("Product deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("products/searchByModelNumber")
    public ResponseEntity<ProductResponseDto> searchProductsByModelNumber(@RequestParam String modelNumber) {
        ProductResponseDto response = productService.searchProductsByModelNumberMethod(modelNumber);
        if(response!=null){
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(404).body(null);
    }

    @GetMapping("products/searchByName")
    public ResponseEntity<List<ProductResponseDto>> searchProductsByName(@RequestParam String name) {
        List<ProductResponseDto> response = productService.searchProductsByNameMethod(name);
        if(response.isEmpty()){
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("products/modelNumber/{modelNumber}")
    public ResponseEntity<String> deleteProductByModelNumber(@PathVariable String modelNumber) {
        boolean isDeleted = productService.deleteProductByModelNumberMethod(modelNumber);
        if (isDeleted) {
            return ResponseEntity.ok("Product deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
