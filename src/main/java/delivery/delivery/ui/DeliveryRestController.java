package delivery.delivery.ui;

import delivery.auth.domain.AuthenticationPrincipal;
import delivery.auth.domain.LoginMember;
import delivery.delivery.application.DeliveryService;
import delivery.delivery.dto.DeliveryRequest;
import delivery.delivery.dto.DeliveryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/deliveries")
public class DeliveryRestController {
    private final DeliveryService deliveryService;

    public DeliveryRestController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping
    public ResponseEntity<DeliveryResponse> create(@AuthenticationPrincipal LoginMember loginMember,
            @RequestBody DeliveryRequest request) {
        DeliveryResponse created = deliveryService.create(request);
        return ResponseEntity.created(URI.create("/deliveries/" + created.getId())).body(created);
    }

    @GetMapping
    public ResponseEntity<List<DeliveryResponse>> findAllByMemberIdAndCreatedDateBetween(
            @AuthenticationPrincipal LoginMember loginMember, String startDate, String endDate) {
        return ResponseEntity.ok(deliveryService.findAllByMemberIdAndCreatedDateBetween(loginMember.getId(), startDate, endDate));
    }
}
