package com_selfProject_ProductService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Product {
    private int productId;
    private String productName;
    private double productPrice;
    private String imageUrl;
    private double productRating;
    private String description;
}
