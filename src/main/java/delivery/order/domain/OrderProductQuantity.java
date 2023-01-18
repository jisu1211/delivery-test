package delivery.order.domain;

import delivery.order.exception.OrderProductExceptionCode;
import delivery.utils.NumberUtil;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrderProductQuantity {
    @Column
    private long quantity;

    protected OrderProductQuantity() {}

    public OrderProductQuantity(long quantity) {
        validate(quantity);
        this.quantity = quantity;
    }

    private void validate(long quantity) {
        if (NumberUtil.isNotPositiveNumber(quantity)) {
            throw new IllegalArgumentException(OrderProductExceptionCode.INVALID_QUANTITY.getMessage());
        }
    }

    public long getQuantity() {
        return quantity;
    }
}
