package com.cart.Cart_Service.Exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private Integer errorCode;

    private String message;

    public CartException(String message) {
        super(message);
        this.message = message;
    }

    public CartException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public CartException(ErrorCodes errorCode, String message) {
        super(message);
        this.errorCode = errorCode.getCode();
        this.message = message;
    }

    public CartException(Integer errorCode, Exception ex) {
        super(ex);
        this.errorCode = errorCode;
        this.message = ex.getMessage();
    }

    public CartException(Exception ex) {
        super(ex);
        this.message = ex.getMessage();
    }
}
