package com_selfProject_ProductService.repository;

import com_selfProject_ProductService.domain.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository {
    Product findByProductName(String productName);
}
