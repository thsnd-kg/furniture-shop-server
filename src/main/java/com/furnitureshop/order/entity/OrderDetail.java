package com.furnitureshop.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.furnitureshop.product.entity.Variant;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders_detail")
public class OrderDetail {
    @JsonIgnore
    @EmbeddedId
    @Setter(AccessLevel.NONE)
    private OrderDetailPK id = new OrderDetailPK();

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @MapsId("variantId")
    @JoinColumn(name = "variant_id")
    private Variant variant;

    @JsonIgnore
    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "quantity",
            nullable = false)
    private Integer quantity;
}
