package ru.gb.gborderhandler.model.mapper;

import org.mapstruct.Mapper;
import ru.gb.gborderhandler.entity.Order;
import ru.gb.gborderhandler.model.OrderDto;

@Mapper
public interface OrderMapper {

    Order toOrder(OrderDto orderDto);

    OrderDto toOrderDto(Order order);

}
