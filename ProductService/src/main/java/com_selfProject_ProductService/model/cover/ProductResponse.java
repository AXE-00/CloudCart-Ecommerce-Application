package com_selfProject_ProductService.model.cover;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductResponse<T> {

    /**
     * Generic data which will adapt to the different resources
     */
    private T data;

    /**
     * The success/error message of the API requested.
     */
    private String message;

    /**
     * The parameter which indicates the status of API response.
     */
    private boolean success;

    /**
     * The application specific error codes.
     */
    private String errorCode;

    /**
     * The hateoas resource link path of the API will display To-Do: add dependency
     * to pom.xml
     */
    private String path;


}
