package com_selfProject_ProductService.exception;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ProductExceptionHandler {
    @ExceptionHandler(value = ProductException.class)
    public ErrorResponse handleContentNotFoundException(ProductException e, HttpServletResponse response) {
        response.setStatus(e.getErrorCode());
        ErrorResponse error = new ErrorResponse();
        error.setMessage(e.getMessage());
        error.setErrorCode(e.getErrorCode());
        error.setSuccess(false);
        return error;
    }
}
