package delivery.order.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private OrderProductQuantity quantity;

    protected OrderProduct() {}

    public OrderProduct(Order order, long quantity) {
        updateOrder(order);

        this.quantity = new OrderProductQuantity(quantity);
    }

    void updateOrder(Order order) {
        if (this.order != order) {
            this.order = order;
            order.addOrderProduct(this);
        }
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public long getQuantity() {
        return this.quantity.getQuantity();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrderProduct that = (OrderProduct) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
