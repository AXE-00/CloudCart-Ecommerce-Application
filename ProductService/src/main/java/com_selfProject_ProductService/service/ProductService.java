package com_selfProject_ProductService.service;

import com_selfProject_ProductService.domain.Product;
import com_selfProject_ProductService.exception.ProductNotFoundException;

import java.util.List;

public interface ProductService {
    Product addProducts(Product product, int productId) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product getByName(String productName);

    Product updateProduct(Product product, int productId);

    boolean deleteById(int productId);
}
