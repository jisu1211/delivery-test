package delivery.order.dto;

import delivery.delivery.domain.Delivery;
import delivery.order.domain.Order;
import delivery.order.domain.OrderProduct;
import delivery.order.domain.OrderStatus;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class OrderRequest {
    private Long memberId;
    private Long deliveryId;
    private List<OrderProductRequest> orderProducts;

    public OrderRequest() {}

    public OrderRequest(Long memberId, Long deliveryId, List<OrderProductRequest> orderProducts) {
        this.memberId = memberId;
        this.deliveryId = deliveryId;
        this.orderProducts = orderProducts;
    }

    public Order toOrder(Long memberId, Delivery delivery, OrderStatus orderStatus) {
        Order order = new Order(memberId, delivery, orderStatus);
        List<OrderProduct> items = orderProducts.stream()
                .map(orderProduct -> orderProduct.toOrderProduct(order))
                .collect(toList());
        order.order(items);

        return order;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public List<OrderProductRequest> getOrderProducts() {
        return orderProducts;
    }
}
