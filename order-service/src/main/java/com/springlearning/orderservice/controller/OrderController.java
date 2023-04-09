package com.springlearning.orderservice.controller;

import com.springlearning.orderservice.dto.OrderRequest;
import com.springlearning.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createOrder(@RequestBody OrderRequest orderRequest){
        orderService.createOrder(orderRequest);
        return "Order Saved Successfully";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void getAllOrders(@RequestBody OrderRequest orderRequest){
        orderService.createOrder(orderRequest);
    }


}
