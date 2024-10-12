package com.cart.Cart_Service.Exception;

public enum ErrorCodes {

    // Informational responses (100–199)
    CONSTRAINT_VIOLATION(101),

    // Successful responses (200–299)
    OK(200), CREATED(201), ACCEPTED(202), NO_CONTENT(204), PARTIAL_CONTENT(206),

    // Client error responses (400–499)
    BAD_REQUEST(400), UNAUTHORIZED(401), ACCESS_DENIED(403), NOT_FOUND(404), METHOD_NOT_SUPPORTED(405),
    NOT_ACCEPTABLE(406), CONFLICT(409), INVALID_OPERATION(412), INVALID_CUSTOMER(413),

    // Server error responses (500–599)
    INTERNAL_SERVER_ERROR(500);

    private int code;

    ErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}