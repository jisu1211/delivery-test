package delivery.delivery.domain;

import delivery.common.BaseEntity;
import delivery.member.domain.Address;
import delivery.order.domain.Order;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Delivery extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    protected Delivery() {}

    public Delivery(Long memberId, Address address, DeliveryStatus deliveryStatus) {
        this.memberId = memberId;
        this.address = address;
        this.deliveryStatus = deliveryStatus;
    }

    public void updateOrder(Order order) {
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public Order getOrder() {
        return order;
    }

    public Address getAddress() {
        return address;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Delivery delivery = (Delivery) o;
        return Objects.equals(id, delivery.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
