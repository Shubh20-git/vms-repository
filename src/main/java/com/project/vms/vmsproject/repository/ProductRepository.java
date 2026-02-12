package com.project.vms.vmsproject.repository;

import com.project.vms.vmsproject.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByModelNumber(String modelNumber);
    List<Product> findByName(String name);
}
