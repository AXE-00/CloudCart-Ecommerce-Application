package com_selfProject_ProductService.controller;

import com_selfProject_ProductService.domain.Product;
import com_selfProject_ProductService.exception.ProductAlreadyExistsException;
import com_selfProject_ProductService.exception.ProductNotFoundException;
import com_selfProject_ProductService.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getAllProducts")
    public ResponseEntity<?> getAllProducts() throws ProductNotFoundException {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/getByName/{productName}")
    public ResponseEntity<?> getByName(@PathVariable String productName) {
        return new ResponseEntity<>(productService.getByName(productName), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return new ResponseEntity<>(productService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(HttpServletRequest httpServletRequest, @RequestBody Product product, @PathVariable int id) {
        if (httpServletRequest.getAttribute("attr2").equals("adminRole")) {
            return new ResponseEntity<>(productService.updateProduct(product, id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("You are not authorized to update", HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(HttpServletRequest httpServletRequest, @PathVariable int id) {
        if (httpServletRequest.getAttribute("attr2").equals("adminRole")) {
            return new ResponseEntity<>(productService.deleteById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("You are not authorized to delete", HttpStatus.OK);
        }
    }
}
