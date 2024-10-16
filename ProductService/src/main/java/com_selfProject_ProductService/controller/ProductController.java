package com_selfProject_ProductService.controller;

import com_selfProject_ProductService.component.Translator;
import com_selfProject_ProductService.domain.Product;
import com_selfProject_ProductService.exception.ProductAlreadyExistsException;
import com_selfProject_ProductService.exception.ProductNotFoundException;
import com_selfProject_ProductService.model.cover.ProductResponse;
import com_selfProject_ProductService.model.cover.ResponseHelper;
import com_selfProject_ProductService.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/productService")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addNewProduct")
    public ResponseEntity<?> addProduct(HttpServletRequest httpServletRequest, @RequestBody Product product,@RequestParam String supRole) throws ProductAlreadyExistsException {
        String role = (String)httpServletRequest.getAttribute("attr2");

        if ("adminRole".equals(role) || "supplier".equals(supRole)) {
            return new ResponseEntity<>(productService.addProducts(product), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("You are not authorized to add new products", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping(value = "/getAllProducts")
    @SuppressWarnings(value = "unchecked")
    public ProductResponse<List<Product>> getAllProducts() throws ProductNotFoundException {
        List<Product> productResponse = productService.getAllProducts();
        return ResponseHelper.createResponse(new ProductResponse<List<Product>>(), productResponse,
                Translator.toLocale("get.all.product.success",null),
                Translator.toLocale("get.all.product.failed",null));
    }

    @GetMapping("/getProducts")
    @SuppressWarnings(value = "unchecked")
    public ProductResponse<Page<Product>> getProducts(@RequestParam int pageNum , @RequestParam int pageSize){

        Page<Product> productResponse = productService.getProducts(pageNum,pageSize);
        return ResponseHelper.createResponse(new ProductResponse<Page<Product>>(), productResponse,
                Translator.toLocale("paginated.product.fetched",null),
                Translator.toLocale("failed.to.fetch",null));

    }

    @GetMapping("/getByName/{productName}")
    public ResponseEntity<?> getByName(@PathVariable String productName) {
        return new ResponseEntity<>(productService.getByName(productName), HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    @SuppressWarnings("unchecked")
    public ProductResponse<Product> getById(@PathVariable int id) {
        Product response = productService.getById(id);
        return ResponseHelper.createResponse(new ProductResponse<Product>(),response,
                Translator.toLocale("product.fetched.success",null),
                Translator.toLocale("product.fetched.failed",null));
    }

    @GetMapping("/getByCategory/{category}")
    public ResponseEntity<?> getByCategory(@PathVariable String category){
        return new ResponseEntity<>(productService.getProductByCategory(category),HttpStatus.OK);
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
