package com_selfProject_ProductService.service;

import com_selfProject_ProductService.domain.Product;
import com_selfProject_ProductService.exception.ProductNotFoundException;

import java.util.List;


public class ProductServiceImpl implements ProductService {
    @Override
    public Product addProducts(Product product, int productId) throws ProductNotFoundException {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product getByName(String productName) {
        return null;
    }

    @Override
    public Product updateProduct(Product product, int productId) {
        return null;
    }

    @Override
    public boolean deleteById(int productId) {
        return false;
    }
}
