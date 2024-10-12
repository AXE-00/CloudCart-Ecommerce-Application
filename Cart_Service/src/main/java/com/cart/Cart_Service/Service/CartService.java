package com.cart.Cart_Service.Service;

import com.cart.Cart_Service.Entity.Cart;
import com.cart.Cart_Service.Request.CartRequest;
import com.cart.Cart_Service.Request.ProductRemoveRequest;
import com.cart.Cart_Service.Response.CartDetailsResponse;
import com.cart.Cart_Service.Response.CartResponse;

import java.util.Optional;

public interface CartService {
    public CartResponse addOrUpdateCart(CartRequest request);
    public boolean removeProductFromCart(ProductRemoveRequest request);
    public CartDetailsResponse getCartDetails(String userId);
}
