package com.cart.Cart_Service.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartResponse {
    private String message;
    private boolean status;
}
