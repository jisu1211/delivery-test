package delivery.delivery.application;

import delivery.delivery.domain.Delivery;
import delivery.delivery.domain.DeliveryRepository;
import delivery.delivery.dto.DeliveryRequest;
import delivery.delivery.dto.DeliveryResponse;
import delivery.delivery.exception.DeliveryExceptionCode;
import delivery.exception.EntityNotFoundException;
import delivery.exception.EntityNotFoundExceptionCode;
import delivery.member.domain.Member;
import delivery.member.domain.MemberRepository;
import delivery.order.domain.OrderRepository;
import delivery.utils.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeliveryService {
    private static final int SEARCH_LIMIT_DATE = 3;

    private final MemberRepository memberRepository;
    private final DeliveryRepository deliveryRepository;

    public DeliveryService(MemberRepository memberRepository, OrderRepository orderRepository,
            DeliveryRepository deliveryRepository) {
        this.memberRepository = memberRepository;
        this.deliveryRepository = deliveryRepository;
    }

    @Transactional
    public DeliveryResponse create(DeliveryRequest request) {
        Member member = findMemberById(request.getMemberId());
        Delivery delivery = request.toDelivery(request.getMemberId(), member.getAddress());
        return DeliveryResponse.of(deliveryRepository.save(delivery));
    }

    private Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(EntityNotFoundExceptionCode.NOT_FOUND_BY_ID));
    }

    @Transactional(readOnly = true)
    public List<DeliveryResponse> findAllByMemberIdAndCreatedDateBetween(Long memberId, String startDate, String endDate) {
        LocalDateTime startDateTime = DateUtil.convertToLocalDateTime(startDate, null);
        LocalDateTime endDateTime = DateUtil.convertToLocalDateTime(endDate, null);

        if (DateUtil.getDiffDays(startDateTime, endDateTime) > SEARCH_LIMIT_DATE) {
            throw new IllegalArgumentException(DeliveryExceptionCode.EXCEEDED_SEARCH_DATE.getMessage());
        }

        return DeliveryResponse.list(deliveryRepository.findAllByMemberIdAndCreatedDateBetween(memberId, startDateTime, endDateTime));
    }
}
