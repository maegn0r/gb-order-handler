package ru.gb.gborderhandler.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.gborderhandler.dao.OrderDao;
import ru.gb.gborderhandler.entity.Order;
import ru.gb.gborderhandler.entity.OrderItem;
import ru.gb.gborderhandler.model.OrderDto;
import ru.gb.gborderhandler.model.ProductDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderDao orderDao;
    public OrderDto create(List<ProductDto> productDtos, String address){
        return new OrderDto(orderDao.save(createNewOrderFromDtos(productDtos, address)));
    }



    private Order createNewOrderFromDtos(List<ProductDto> productDtos, String address){
        Order order = new Order();
        order.setOrderItems(productDtos.stream().map(OrderItem::new).peek(item -> item.setOrder(order)).collect(Collectors.toList()));
        order.setAddress(address);
        return order;
    }
}
