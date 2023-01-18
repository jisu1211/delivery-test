package delivery.order.domain;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Embeddable
public class OrderProducts {
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    protected OrderProducts() {}

    public void addOrderProduct(Order order, OrderProduct orderLineItem) {
        if (!hasOrderProduct(orderLineItem)) {
            this.orderProducts.add(orderLineItem);
            orderLineItem.updateOrder(order);
        }
    }

    private boolean hasOrderProduct(OrderProduct orderLineItem) {
        return this.orderProducts.contains(orderLineItem);
    }

    public List<OrderProduct> getOrderProducts() {
        return Collections.unmodifiableList(orderProducts);
    }
}
