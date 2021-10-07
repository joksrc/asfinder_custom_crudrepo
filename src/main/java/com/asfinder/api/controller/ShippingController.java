package com.asfinder.api.controller;

import com.asfinder.api.model.Order;
import com.asfinder.api.service.ShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/shippingController")
@RequiredArgsConstructor
public class ShippingController {

    private final ShippingService shippingService;

    @RequestMapping(value = "/toOrder", method = RequestMethod.POST)
    public void addUser(@RequestBody Order order){

        shippingService.toOrder(order);

    }
}
