package com_selfProject_ProductService.repository;

import com_selfProject_ProductService.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, Integer> {
    Product findByProductName(String productName);
}
