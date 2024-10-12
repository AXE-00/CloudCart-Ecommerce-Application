package com.cart.Cart_Service.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartRequest {
    private String userId;
    private String userEmail;
    private Integer productId;
}
