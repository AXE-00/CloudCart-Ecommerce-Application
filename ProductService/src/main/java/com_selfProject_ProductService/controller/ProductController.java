package com_selfProject_ProductService.controller;

import com_selfProject_ProductService.domain.Product;
import com_selfProject_ProductService.exception.ProductAlreadyExistsException;
import com_selfProject_ProductService.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addNewProduct")
    public ResponseEntity<?> addProduct(HttpServletRequest httpServletRequest, @RequestBody Product product) throws ProductAlreadyExistsException {
        if (httpServletRequest.getAttribute("attr2").equals("adminRole")) {
            return new ResponseEntity<>(productService.addProducts(product), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("You are not authorized to add new products", HttpStatus.UNAUTHORIZED);
        }
    }
}
