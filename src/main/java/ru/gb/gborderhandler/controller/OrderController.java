package ru.gb.gborderhandler.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.gborderhandler.model.OrderDto;
import ru.gb.gborderhandler.model.ProductDto;
import ru.gb.gborderhandler.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;


    @PostMapping(produces = "application/json;charset=UTF-8",
            consumes = "application/json;charset=UTF-8")
    public OrderDto create(@RequestBody List<ProductDto> products, @RequestParam String address) {
        return orderService.create(products, address);
    }

}
