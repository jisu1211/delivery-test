package delivery.delivery.dto;

import delivery.delivery.domain.Delivery;
import delivery.delivery.domain.DeliveryStatus;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class DeliveryResponse {
    private Long id;
    private Long orderId;
    private String city;
    private String street;
    private String zipcode;
    private DeliveryStatus deliveryStatus;

    public DeliveryResponse() {}

    public DeliveryResponse(Delivery delivery) {
        this.id = delivery.getId();
        this.orderId = delivery.getOrder().getId();
        this.city = delivery.getAddress().getCity();
        this.street = delivery.getAddress().getStreet();
        this.zipcode = delivery.getAddress().getZipcode();
        this.deliveryStatus = delivery.getDeliveryStatus();
    }

    public static DeliveryResponse of(Delivery delivery) {
        return new DeliveryResponse(delivery);
    }

    public static List<DeliveryResponse> list(List<Delivery> deliveries) {
        return deliveries.stream()
                .map(DeliveryResponse::new)
                .collect(toList());
    }

    public Long getId() {
        return id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }
}
