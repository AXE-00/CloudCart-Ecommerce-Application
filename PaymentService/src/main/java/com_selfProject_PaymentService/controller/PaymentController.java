package com_selfProject_PaymentService.controller;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com_selfProject_PaymentService.domain.PaymentOrder;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {
    @PostMapping("/createOrder")
    @ResponseBody
    public ResponseEntity<?> createOrder(@RequestBody PaymentOrder paymentOrder) {
        com.razorpay.Order order;
        try {
            var client = new RazorpayClient("rzp_test_Nv2JwuXBqlVpsN", "GXNVuxdg7aYApF9FxOWnqrbn");
            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", paymentOrder.getOrderAmount() * 100);
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "tnx_235425");
            order = client.orders.create(orderRequest);
        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(order.toString(), HttpStatus.CREATED);
    }

}
