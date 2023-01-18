package delivery.order.dto;

import delivery.order.domain.OrderProduct;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class OrderProductResponse {
    private Long id;
    private Long productId;
    private long quantity;

    public OrderProductResponse() {}

    public OrderProductResponse(OrderProduct orderProduct) {
        this.id = orderProduct.getId();
        this.productId = orderProduct.getProductId();
        this.quantity = orderProduct.getQuantity();
    }

    public static List<OrderProductResponse> list(List<OrderProduct> orderProducts) {
        return orderProducts.stream()
                .map(OrderProductResponse::new)
                .collect(toList());
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public long getQuantity() {
        return quantity;
    }
}
