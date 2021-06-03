package com.rensource.videorental.controllers;

import com.rensource.videorental.dtos.OrderDto;
import com.rensource.videorental.models.Order;
import com.rensource.videorental.responses.Response;
import com.rensource.videorental.services.orders.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {

    OrderService orderService;
    ModelMapper modelMapper;

    @Autowired
    public OrderController(OrderService orderService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<Response<Order>> makeOrder(@Valid @RequestBody OrderDto orderDto) {
        Order order = orderService.makeOrder(modelMapper.map(orderDto, Order.class));
        Response<Order> response = new Response<>();
        if (order != null) {
            response.setStatus(HttpStatus.CREATED);
            response.setMessage("Order successfully made");
            response.setData(order);
        } else {
            response.setMessage("No such video available");
            response.setStatus(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
