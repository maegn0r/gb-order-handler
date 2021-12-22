package ru.gb.gborderhandler.model;

import lombok.*;
import ru.gb.gborderhandler.entity.Order;
import ru.gb.gborderhandler.entity.OrderItem;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    private String address;
    private List<OrderItemDto> orderItems;



    public OrderDto(Order order){
        this.id = order.getId();
        this.address = order.getAddress();
        this.orderItems = order.getOrderItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
    }

}
