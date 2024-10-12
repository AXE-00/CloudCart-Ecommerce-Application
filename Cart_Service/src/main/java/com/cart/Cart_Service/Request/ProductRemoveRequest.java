package com.cart.Cart_Service.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRemoveRequest {
    private String userId;
    private Integer productId;
}
