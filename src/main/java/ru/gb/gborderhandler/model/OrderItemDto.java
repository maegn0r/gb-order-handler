package ru.gb.gborderhandler.model;

import lombok.*;
import ru.gb.gborderhandler.entity.OrderItem;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDto {
    private Long id;
    private Long productId;
    private String productName;
    private int count;

    public OrderItemDto(OrderItem orderItem) {
        this.id = orderItem.getId();
        this.productId = orderItem.getProductId();
        this.productName = orderItem.getProductName();
        this.count = orderItem.getCount();
    }
}
