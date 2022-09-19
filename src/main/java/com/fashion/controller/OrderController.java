package com.fashion.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fashion.dto.CheckoutItemDto;
import com.fashion.dto.StripeResponse;
import com.fashion.service.AuthenticationService;
import com.fashion.service.OrderService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

@RestController
@RequestMapping("/ecom/order")
public class OrderController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/create-checkout-session")
    public ResponseEntity<StripeResponse> checkoutList(@RequestBody List<CheckoutItemDto> checkoutItemDtoList)
            throws StripeException {
        Session session = orderService.createSession(checkoutItemDtoList);
        StripeResponse stripeResponse = new StripeResponse(session.getId());
        return new ResponseEntity<>(stripeResponse, HttpStatus.OK);

    }

}