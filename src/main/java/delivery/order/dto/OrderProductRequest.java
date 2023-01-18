package delivery.order.dto;

import delivery.order.domain.Order;
import delivery.order.domain.OrderProduct;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class OrderProductRequest {
    private Long productId;
    private long quantity;

    public OrderProductRequest() {}

    public OrderProductRequest(Long productId, long quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public static List<OrderProductRequest> list(List<OrderProduct> orderProducts) {
        return orderProducts.stream()
                .map(orderProduct -> new OrderProductRequest(orderProduct.getProductId(), orderProduct.getQuantity()))
                .collect(toList());
    }

    public OrderProduct toOrderProduct(Order order) {
        return new OrderProduct(order, quantity);
    }

    public Long getProductId() {
        return productId;
    }

    public long getQuantity() {
        return quantity;
    }
}
