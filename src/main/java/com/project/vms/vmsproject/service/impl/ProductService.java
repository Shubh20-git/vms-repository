package com.project.vms.vmsproject.service.impl;

import com.project.vms.vmsproject.dto.productdto.ProductRequestDto;
import com.project.vms.vmsproject.dto.productdto.ProductResponseDto;
import com.project.vms.vmsproject.exceptions.CustomExceptions;
import com.project.vms.vmsproject.model.Product;
import com.project.vms.vmsproject.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;

    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
       Product p = modelMapper.map(productRequestDto, Product.class);
       Product savedProduct = productRepository.save(p);
       return modelMapper.map(savedProduct, ProductResponseDto.class);
    }
    public String deleteAllProducts(){
        productRepository.deleteAll();
        return "All Products Deleted Successfully";
    }

    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> modelMapper.map(product, ProductResponseDto.class))
                .toList();
    }

    public ProductResponseDto getProductsById(Long id) {
        Optional<Product> products = productRepository.findById(id);
        return products.stream()
                .map(product -> modelMapper.map(product, ProductResponseDto.class))
                .findFirst()
                .orElse(null);
    }
    public boolean deleteById(Long id) {
        if(!productRepository.existsById(id)) {
            throw new CustomExceptions.ProductNotFoundException("Product with id" + id + " not found");
        }
        productRepository.deleteById(id);
        return true;
    }

    public ProductResponseDto searchProductsByModelNumberMethod(String modelNumber) {
        Product product = productRepository.findByModelNumber(modelNumber);
        if(product != null) {
            return modelMapper.map(product, ProductResponseDto.class);
        }
        return null;

    }

    public List<ProductResponseDto> searchProductsByNameMethod(String name) {
        List<Product> products = productRepository.findByName(name);
        List<ProductResponseDto> listProducts = new ArrayList<>();
        for(Product product : products) {
            listProducts.add(modelMapper.map(product, ProductResponseDto.class));
        }
        return listProducts;
    }

    public boolean deleteProductByModelNumberMethod(String modelNumber) {
        Product products = productRepository.findByModelNumber(modelNumber);
        if(products == null) {
            throw new CustomExceptions.ProductNotFoundException("Product with model number " + modelNumber + " not found");
        }
        productRepository.delete(products);
        return true;
    }
}
