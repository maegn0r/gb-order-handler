package ru.gb.gborderhandler.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.gborderhandler.entity.Order;

public interface OrderDao extends JpaRepository<Order, Long> {
}
