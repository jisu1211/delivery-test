package delivery.order.domain;

import delivery.common.BaseEntity;
import delivery.delivery.domain.Delivery;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    @Embedded
    private OrderProducts orderProducts = new OrderProducts();

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Delivery delivery;

    protected Order() {}

    public Order(Long memberId, Delivery delivery, OrderStatus orderStatus) {
        this.memberId = memberId;
        this.orderStatus = orderStatus;

        updateDelivery(delivery);
    }

    private void updateDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.updateOrder(this);
    }

    public void order(List<OrderProduct> orderProducts) {
        orderProducts.forEach(this::addOrderProduct);
    }

    void addOrderProduct(OrderProduct orderProduct) {
        this.orderProducts.addOrderProduct(this, orderProduct);
    }

    public Long getId() {
        return id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public List<OrderProduct> getOrderProducts() {
        return this.orderProducts.getOrderProducts();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
