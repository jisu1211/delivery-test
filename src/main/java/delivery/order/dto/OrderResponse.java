package delivery.order.dto;

import delivery.order.domain.Order;
import delivery.order.domain.OrderStatus;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class OrderResponse {
    private Long id;
    private Long deliveryId;
    private Long memberId;
    private OrderStatus orderStatus;
    private List<OrderProductResponse> orderProducts;

    public OrderResponse() {}

    public OrderResponse(Order order) {
        this.id = order.getId();
        this.deliveryId = order.getDelivery().getId();
        this.memberId = order.getMemberId();
        this.orderStatus = order.getOrderStatus();
        this.orderProducts = OrderProductResponse.list(order.getOrderProducts());
    }

    public static OrderResponse of(Order order) {
        return new OrderResponse(order);
    }

    public static List<OrderResponse> list(List<Order> orders) {
        return orders.stream()
                .map(OrderResponse::new)
                .collect(toList());
    }

    public Long getId() {
        return id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public List<OrderProductResponse> getOrderProducts() {
        return orderProducts;
    }
}
