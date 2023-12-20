package com_selfProject_ProductService.service;

import com_selfProject_ProductService.domain.Product;
import com_selfProject_ProductService.exception.ProductAlreadyExistsException;
import com_selfProject_ProductService.exception.ProductNotFoundException;

import java.util.List;

public interface ProductService {
    Product addProducts(Product product) throws ProductAlreadyExistsException;

    List<Product> getAllProducts() throws ProductNotFoundException;

    Product getByName(String productName);

    Product getById(int productId);

    Product updateProduct(Product product, int productId);

    boolean deleteById(int productId);
}
