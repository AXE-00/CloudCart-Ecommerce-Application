package com.cart.Cart_Service.model.cover;

import com.cart.Cart_Service.Exception.CartException;
import com.cart.Cart_Service.Exception.ErrorCodes;

public class ResponseHelper {
    /**
     * Use when the API return some response class.
     * <p>
     * Use this while do the POST or PUT
     *
     * @param response
     * @param data
     * @param successMessage
     * @param errorMessage
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static RudraCartResponse createResponse(RudraCartResponse response, Object data, String successMessage,
                                             String errorMessage) {

        if (data != null) {
            response.setSuccess(true);
            response.setData(data);
            response.setMessage(successMessage);
        } else {
            throw new CartException(ErrorCodes.INTERNAL_SERVER_ERROR, errorMessage);
        }
        return response;
    }

    /**
     * Use when the API return some response class.
     * <p>
     * Use this while do GET APIs
     *
     * @param response
     * @param data
     * @param successMessage
     * @param errorMessage
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static RudraCartResponse responseForGetOrFeign(RudraCartResponse response, Object data, String successMessage,
                                                    String errorMessage) {
        if (data != null) {
            response.setSuccess(true);
            response.setData(data);
            response.setMessage(successMessage);
        } else {
            throw new CartException(ErrorCodes.NO_CONTENT, errorMessage);
        }
        return response;
    }

    /**
     * Use this format when the API returns only boolean
     *
     * @param response
     * @param flag
     * @param successMessage
     * @param errorMessage
     * @return
     */
    @SuppressWarnings({ "rawtypes", "all" })
    public static RudraCartResponse createResponseForFlags(RudraCartResponse response, boolean flag, String successMessage,
                                                     String errorMessage) {
        if (flag) {
            response.setSuccess(flag);
            response.setData(flag);
            response.setMessage(successMessage);
        } else {
            throw new CartException(ErrorCodes.OK, errorMessage);
        }
        return response;
    }
}
