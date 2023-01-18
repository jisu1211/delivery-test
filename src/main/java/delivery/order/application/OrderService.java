package delivery.order.application;

import delivery.delivery.domain.Delivery;
import delivery.delivery.domain.DeliveryRepository;
import delivery.exception.EntityNotFoundException;
import delivery.exception.EntityNotFoundExceptionCode;
import delivery.order.domain.*;
import delivery.order.dto.OrderRequest;
import delivery.order.dto.OrderResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final DeliveryRepository deliveryRepository;

    public OrderService(OrderRepository orderRepository, DeliveryRepository deliveryRepository) {
        this.orderRepository = orderRepository;
        this.deliveryRepository = deliveryRepository;
    }

    @Transactional
    public OrderResponse create(OrderRequest request) {
        Delivery delivery = findDeliveryById(request.getDeliveryId());
        Order order = request.toOrder(request.getMemberId(), delivery, OrderStatus.ORDER);
        return OrderResponse.of(orderRepository.save(order));
    }

    private Delivery findDeliveryById(Long deliveryId) {
        return deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new EntityNotFoundException(EntityNotFoundExceptionCode.NOT_FOUND_BY_ID));
    }
}
