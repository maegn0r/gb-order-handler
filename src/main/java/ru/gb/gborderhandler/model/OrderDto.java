package ru.gb.gborderhandler.model;

import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    private String address;
    private List<OrderItemDto> orderItems;

    }
