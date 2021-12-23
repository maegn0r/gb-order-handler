package ru.gb.gborderhandler.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.gborderhandler.dao.OrderDao;
import ru.gb.gborderhandler.entity.Order;
import ru.gb.gborderhandler.entity.OrderItem;
import ru.gb.gborderhandler.model.OrderDto;
import ru.gb.gborderhandler.model.ProductDto;
import ru.gb.gborderhandler.model.mapper.OrderMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderDao orderDao;
    private final OrderMapper orderMapper;

    public OrderDto create(List<ProductDto> productDtos, String address) {

        return orderMapper.toOrderDto(orderDao.save(createNewOrderFromDtos(productDtos, address)));
    }


    private Order createNewOrderFromDtos(List<ProductDto> productDtos, String address) {
        Order order = new Order();
        order.setOrderItems(productDtos.stream().map(OrderItem::new).peek(item -> item.setOrder(order)).collect(Collectors.toList()));
        order.setAddress(address);
        return order;
    }

    public void deleteById(Long id) {
        orderDao.deleteById(id);
    }

    public OrderDto addToOrder(Long id, ProductDto productDto) {
        Order orderFromDb = orderDao.findById(id).get();
        Optional<OrderItem> updatingOrderItem = orderFromDb.getOrderItems().stream().filter(item -> item.getProductId().equals(productDto.getId())).findFirst();
        if (updatingOrderItem.isPresent()) {
            updatingOrderItem.get().setCount(updatingOrderItem.get().getCount() + 1);
        } else {
            OrderItem newOrderItem = new OrderItem(productDto);
            newOrderItem.setOrder(orderFromDb);
            orderFromDb.getOrderItems().add(newOrderItem);
        }
        return orderMapper.toOrderDto(orderDao.save(orderFromDb));
    }

    public OrderDto removeFromOrder(Long id, ProductDto productDto) {
        Order orderFromDb = orderDao.findById(id).get();
        OrderItem updatingOrderItem = orderFromDb.getOrderItems().stream().filter(item -> item.getProductId().equals(productDto.getId())).findFirst().orElseThrow(RuntimeException::new);
        if (updatingOrderItem.getCount() == 1) {
            orderFromDb.getOrderItems().remove(updatingOrderItem);
            updatingOrderItem.setOrder(null);
        } else {
            updatingOrderItem.setCount(updatingOrderItem.getCount() - 1);
        }
        return orderMapper.toOrderDto(orderDao.save(orderFromDb));
    }

}
