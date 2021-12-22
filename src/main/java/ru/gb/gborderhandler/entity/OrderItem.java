package ru.gb.gborderhandler.entity;

import lombok.*;
import ru.gb.gborderhandler.model.ProductDto;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "count")
    private int count;

    @ManyToOne()
    @JoinColumn(name = "order_id")
    private Order order;


    public OrderItem(ProductDto productDto){
        this.count = 1;
        this.productId = productDto.getId();
        this.productName = productDto.getTitle();
    }
}
