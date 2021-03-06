package com.furnitureshop.order.dto.order;

import com.furnitureshop.order.dto.voucher.GetVoucherDto;
import com.furnitureshop.order.entity.Order;
import com.furnitureshop.order.entity.OrderStatus;
import com.furnitureshop.order.entity.PaymentStatus;
import com.furnitureshop.order.entity.Voucher;
import io.swagger.models.auth.In;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Getter
public class GetOrderDto {
    private final Long orderId;
    private final OrderStatus orderStatus;
    private final PaymentStatus paymentStatus;
    private final String username;
    private final GetVoucherDto voucher;
    private final Integer totalPrice;
    private final List<GetOrderDetailDto> orderDetails;
    private final LocalDateTime createdAt;
    private final Integer discount;

    public GetOrderDto(Order order) {
        this.orderId = order.getOrderId();
        this.orderStatus = order.getOrderStatus();
        this.paymentStatus = order.getPaymentStatus();
        this.username = order.getUser().getUsername();
        this.voucher = order.getVoucher() == null
                ? null 
                : new GetVoucherDto(order.getVoucher());
        this.totalPrice = order.getTotalPrice();
        this.createdAt = order.getCreatedAt();
        this.orderDetails = order.getOrderDetails().isEmpty()
                ? new ArrayList<>()
                : order.getOrderDetails().stream().map(GetOrderDetailDto::new).collect(Collectors.toList());
        this.orderDetails.sort(Comparator.comparing(o -> o.getVariant().getVariantId()));
        this.discount = order.getDiscount();
    }
}
