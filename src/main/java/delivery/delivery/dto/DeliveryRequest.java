package delivery.delivery.dto;

import delivery.delivery.domain.Delivery;
import delivery.delivery.domain.DeliveryStatus;
import delivery.member.domain.Address;

public class DeliveryRequest {
    private Long memberId;
    private Long orderId;
    private DeliveryStatus deliveryStatus;

    public DeliveryRequest() {}

    public DeliveryRequest(Long memberId, Long orderId, DeliveryStatus deliveryStatus) {
        this.memberId = memberId;
        this.orderId = orderId;
        this.deliveryStatus = deliveryStatus;
    }

    public Delivery toDelivery(Long memberId, Address address) {
        return new Delivery(memberId, address, deliveryStatus);
    }

    public Long getMemberId() {
        return memberId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }
}
