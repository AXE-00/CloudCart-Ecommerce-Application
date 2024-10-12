package com.cart.Cart_Service.Exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
@RestController
public class CartExceptionHandler {

    @ExceptionHandler(value = CartException.class)
    public ErrorResponse handleContentNotFoundException(CartException e, HttpServletResponse response) {
        response.setStatus(e.getErrorCode());
        ErrorResponse error = new ErrorResponse();
        error.setMessage(e.getMessage());
        error.setErrorCode(e.getErrorCode());
        error.setSuccess(false);
        return error;
    }


}
