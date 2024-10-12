package com.cart.Cart_Service.Controller;

import com.cart.Cart_Service.Component.Translator;
import com.cart.Cart_Service.Request.CartRequest;
import com.cart.Cart_Service.Request.ProductRemoveRequest;
import com.cart.Cart_Service.Response.CartDetailsResponse;
import com.cart.Cart_Service.Response.CartResponse;
import com.cart.Cart_Service.Service.CartService;
import com.cart.Cart_Service.model.cover.ResponseHelper;
import com.cart.Cart_Service.model.cover.RudraCartResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/addProduct")
    @SuppressWarnings("unchecked")
    public RudraCartResponse<CartResponse> addProductToCart(HttpServletRequest httpRequest,@RequestBody CartRequest request){
        CartResponse response = cartService.addOrUpdateCart(request);
        return ResponseHelper.createResponse(new RudraCartResponse<CartResponse>(), response,
                Translator.toLocale("product.cart.added.success",null),
                Translator.toLocale("product.cart.added.failed",null));
    }

    @PostMapping("/remove/product")
    @SuppressWarnings("unchecked")
    public RudraCartResponse<Boolean> removeProductFromCart(HttpServletRequest httpRequest,@RequestBody ProductRemoveRequest request){
        boolean response = cartService.removeProductFromCart(request);
        return ResponseHelper.createResponseForFlags(new RudraCartResponse<Boolean>(),response,
                Translator.toLocale("product.cart.removed.success",null),
                Translator.toLocale("product.cart.removed.failed",null));
    }

    @GetMapping("/getCart/details/{userId}")
    @SuppressWarnings("unchecked")
    public RudraCartResponse<CartDetailsResponse> getCartDetails(HttpServletRequest httpRequest,@PathVariable String userId){
        CartDetailsResponse response = cartService.getCartDetails(userId);
        return ResponseHelper.createResponse(new RudraCartResponse<CartDetailsResponse>(),response,
                Translator.toLocale("cart.details.fetched.success",null),
                Translator.toLocale("cart.details.fetched.failed",null));
    }
}
