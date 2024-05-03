//package com.example.malpay;
//
//import com.stripe.Stripe;
//import com.stripe.exception.StripeException;
//import com.stripe.model.checkout.Session;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/payment")
//public class  {
//PaymentController
//@Value("${stripe.secret.key}")
//private String stripeSecretKey;
//
//@PostMapping("/create-session")
//public ResponseEntity<Map<String, Object>> createSession(@RequestBody Map<String, Object> request) {
//
//    Long amount = Long.parseLong(request.get("amount").toString());
//    String productDescription = request.get("productDescription").toString();
//
//    Stripe.apiKey = stripeSecretKey;
//
//    Map<String, Object> params = new HashMap<>();
//    params.put("payment_method_types", new String[]{"card"});
//
//    Map<String, Object> lineItem = new HashMap<>();
//    Map<String, Object> priceData = new HashMap<>();
//    Map<String, Object> productData = new HashMap<>();
//
//    productData.put("name", productDescription);
//
//    priceData.put("currency", "usd");
//    priceData.put("unit_amount", amount);
//    priceData.put("product_data", productData);
//
//    lineItem.put("price_data", priceData);
//    lineItem.put("quantity", 1);
//
//    params.put("line_items", new Object[]{lineItem});
//    params.put("mode", "payment");
//    params.put("success_url", "https://dribbble.com/tags/payment-successful");
//    params.put("cancel_url", "https://dribbble.com/tags/payment-failed");
//
//    try {
//        Session session = Session.create(params);
//
//        Map<String, Object> responseData = new HashMap<>();
//        responseData.put("sessionId", session.getId());
//        responseData.put("url", session.getUrl());
//
//        return new ResponseEntity<>(responseData, HttpStatus.OK);
//    } catch (StripeException e) {
//        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}
//
//@GetMapping("/get-payment-intent")
//public ResponseEntity<String> getPaymentIntent(@RequestParam String sessionId) {
//    Stripe.apiKey = stripeSecretKey;
//
//    try {
//        Session session = Session.retrieve(sessionId);
//        String paymentIntentId = session.getPaymentIntent();
//
//        return new ResponseEntity<>(paymentIntentId, HttpStatus.OK);
//    } catch (StripeException e) {
//        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}
//}
//

package com.example.malpay;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Value("${stripe.api.secretKey}")
    private String stripeSecretKey;

    @PostMapping("/create-session")
    public ResponseEntity<Map<String, Object>> createSession(@RequestBody Map<String, Object> request) {

        Long amount = Long.parseLong(request.get("amount").toString());
        String productDescription = request.get("productDescription").toString();

        Stripe.apiKey = stripeSecretKey;

        Map<String, Object> params = new HashMap<>();
        params.put("payment_method_types", new String[]{"card"});

        Map<String, Object> lineItem = new HashMap<>();
        Map<String, Object> priceData = new HashMap<>();
        Map<String, Object> productData = new HashMap<>();

        productData.put("name", productDescription);

        priceData.put("currency", "usd");
        priceData.put("unit_amount", amount);
        priceData.put("product_data", productData);

        lineItem.put("price_data", priceData);
        lineItem.put("quantity", 1);

        params.put("line_items", new Object[]{lineItem});
        params.put("mode", "payment");
        params.put("success_url", "https://dribbble.com/tags/payment-successful");
        params.put("cancel_url", "https://dribbble.com/tags/payment-failed");

        try {
            Session session = Session.create(params);

            Map<String, Object> responseData = new HashMap<>();
            responseData.put("sessionId", session.getId());
            responseData.put("url", session.getUrl());

            return new ResponseEntity<>(responseData, HttpStatus.OK);
        } catch (StripeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-payment-intent")
    public ResponseEntity<String> getPaymentIntent(@RequestParam String sessionId) {
        Stripe.apiKey = stripeSecretKey;

        try {
            Session session = Session.retrieve(sessionId);
            String paymentIntentId = session.getPaymentIntent();

            return new ResponseEntity<>(paymentIntentId, HttpStatus.OK);
        } catch (StripeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
