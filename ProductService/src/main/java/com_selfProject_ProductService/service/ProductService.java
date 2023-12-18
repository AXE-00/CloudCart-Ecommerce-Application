package com_selfProject_ProductService.service;

import com_selfProject_ProductService.domain.Product;
import com_selfProject_ProductService.exception.ProductNotFoundException;

public interface ProductService {
    Product addProducts(Product product, int productId) throws ProductNotFoundException;

}
