package ru.gb.gborderhandler.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.gborderhandler.model.OrderDto;
import ru.gb.gborderhandler.model.ProductDto;
import ru.gb.gborderhandler.model.mapper.OrderMapper;
import ru.gb.gborderhandler.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;


    @PostMapping(produces = "application/json;charset=UTF-8",
            consumes = "application/json;charset=UTF-8")
    public OrderDto create(@RequestBody List<ProductDto> products, @RequestParam String address) {
        return orderService.create(products, address);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        orderService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/add_to_order/{id}")
    public OrderDto addToOrder(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return orderService.addToOrder(id, productDto);
    }

    @PutMapping("/remove_from_order/{id}")
    public OrderDto removeFromOrder(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return orderService.removeFromOrder(id, productDto);
    }

}
