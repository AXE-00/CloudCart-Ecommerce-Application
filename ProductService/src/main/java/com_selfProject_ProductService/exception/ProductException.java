package com_selfProject_ProductService.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private Integer errorCode;

    private String message;

    public ProductException(String message) {
        super(message);
        this.message = message;
    }

    public ProductException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public ProductException(ErrorCodes errorCode, String message) {
        super(message);
        this.errorCode = errorCode.getCode();
        this.message = message;
    }

    public ProductException(Integer errorCode, Exception ex) {
        super(ex);
        this.errorCode = errorCode;
        this.message = ex.getMessage();
    }

    public ProductException(Exception ex) {
        super(ex);
        this.message = ex.getMessage();
    }
}
