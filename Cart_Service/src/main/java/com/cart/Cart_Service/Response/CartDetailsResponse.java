package com.cart.Cart_Service.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CartDetailsResponse {

    private String userId;
    private List<Integer> productIds;
    private int noOfProducts;
}
