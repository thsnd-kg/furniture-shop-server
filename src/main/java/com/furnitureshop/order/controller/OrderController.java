package com.furnitureshop.order.controller;

import com.furnitureshop.common.ResponseHandler;
import com.furnitureshop.order.dto.order.CreateOrderDto;
import com.furnitureshop.order.dto.order.GetOrderDto;
import com.furnitureshop.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public Object getOrders() {
        try {
            List<GetOrderDto> orders = service.getOrders().stream().map(GetOrderDto::new).collect(Collectors.toList());
            return ResponseHandler.getResponse(orders, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{order-id}")
    public Object getOrder(@PathVariable("order-id") Long orderId) {
        try {
            GetOrderDto order = new GetOrderDto(service.getOrder(orderId));
            return ResponseHandler.getResponse(order, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public Object createOrder(@Valid @RequestBody CreateOrderDto dto, BindingResult errors) {
        try {
            if (errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            GetOrderDto order = new GetOrderDto(service.createOrder(dto));
            return ResponseHandler.getResponse(order, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }
}
