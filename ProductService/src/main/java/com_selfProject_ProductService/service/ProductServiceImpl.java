package com_selfProject_ProductService.service;

import com_selfProject_ProductService.domain.Product;
import com_selfProject_ProductService.exception.ProductAlreadyExistsException;
import com_selfProject_ProductService.exception.ProductNotFoundException;
import com_selfProject_ProductService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProducts(Product product) throws ProductAlreadyExistsException {
        int productId = product.getProductId();
        Optional<Product> optionalProduct = this.productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            throw new ProductAlreadyExistsException("Product with this Id already exists");
        }
        return this.productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() throws ProductNotFoundException {
        List<Product> productList = this.productRepository.findAll();
        if (productList.isEmpty()) {
            throw new ProductNotFoundException("No Product Found");
        }
        return productList;
    }

    @Override
    public Product getByName(String productName){
        return productRepository.findByProductName(productName);
    }

    @Override
    public Product updateProduct(Product product, int productId) throws ProductNotFoundException {
        return null;
    }

    @Override
    public boolean deleteById(int productId) throws ProductNotFoundException {
        return false;
    }
}
