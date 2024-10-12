package com.cart.Cart_Service.Service.Impl;

import com.cart.Cart_Service.Component.Translator;
import com.cart.Cart_Service.Entity.Cart;
import com.cart.Cart_Service.Exception.CartException;
import com.cart.Cart_Service.Exception.ErrorCodes;
import com.cart.Cart_Service.Repository.CartRepo;
import com.cart.Cart_Service.Repository.UserRepo;
import com.cart.Cart_Service.Request.CartRequest;
import com.cart.Cart_Service.Request.ProductRemoveRequest;
import com.cart.Cart_Service.Response.CartDetailsResponse;
import com.cart.Cart_Service.Response.CartResponse;
import com.cart.Cart_Service.Service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Slf4j
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private UserRepo userRepo;


    @Override
    @Transactional
    public CartResponse addOrUpdateCart(CartRequest request) {
        try {
            // Validate the incoming request
            basicValidation(request);

            // Check if the cart already exists for the given userId
//        Optional<Cart> existingCart = cartRepo.findByUserId(request.getUserId());

            Cart cart = new Cart();
            cart.setProductId(request.getProductId());
            cart.setUserId(request.getUserId());
            cart.setAddedOn(LocalDateTime.now());

            // Save the updated or new cart
            cartRepo.save(cart);

            // Prepare the response
            CartResponse cartResponse = new CartResponse();
            cartResponse.setMessage("Product added/updated successfully..!");
            cartResponse.setStatus(true);

            return cartResponse;
        }catch (CartException exception){
            log.error("Exception Occurred while adding the product.");
            throw new CartException(exception.getErrorCode(),exception.getMessage());
        }catch (Exception ex){
            throw new CartException(ErrorCodes.INTERNAL_SERVER_ERROR,"Some internal issue Occurred..!");
        }
    }


    @Override
    @Transactional
    public boolean removeProductFromCart(ProductRemoveRequest request ) {
        try {
            Cart cart = cartRepo.findByProductIdAndUserId(request.getProductId(), request.getUserId())
                    .orElseThrow(() -> new CartException(ErrorCodes.NOT_FOUND, "Cart Not Found"));

            int deletedCart = cartRepo.deleteByProductIdAndUserId(request.getProductId(), request.getUserId());
            return deletedCart>0;
        }catch (CartException e){
            throw new CartException(ErrorCodes.INTERNAL_SERVER_ERROR,"Internal Server Error while removing product");
        }
    }


    @Override
    public CartDetailsResponse getCartDetails(String userId) {
        try {
            if (userId != null) {
                if (userRepo.findById(userId).isEmpty()){
                    log.error("User Not Found with this ID");
                    throw new CartException(ErrorCodes.NOT_FOUND,
                            Translator.toLocale("user.not.found",null));
                }
            }
            CartDetailsResponse response = new CartDetailsResponse();
            List<Integer> productIds = cartRepo.findProductIdByUserId(userId);
            response.setUserId(userId);
            response.setProductIds(productIds);
            response.setNoOfProducts(productIds.size());
            return response;
        }catch (CartException ex){
            throw new CartException(ErrorCodes.NOT_FOUND,ex.getMessage());
        }
    }

    private void basicValidation(CartRequest request){
        try {
            if (request != null) {
                if (userRepo.findByIdAndUserEmail(request.getUserId(), request.getUserEmail())==null) {
                    log.error("User Not Found with this ID");
                    throw new CartException(ErrorCodes.NOT_FOUND, "User Not Found with this Id");
                }
            }
        }catch (CartException exception){
            log.error("Error occurred while finding the cart.");
            throw new CartException(exception.getErrorCode(),exception.getMessage());
        }
    }
}
