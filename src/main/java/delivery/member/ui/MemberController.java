package delivery.member.ui;

import delivery.auth.domain.AuthenticationPrincipal;
import delivery.auth.domain.LoginMember;
import delivery.member.application.MemberService;
import delivery.member.domain.Address;
import delivery.member.dto.MemberRequest;
import delivery.member.dto.MemberResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<MemberResponse> createMember(@RequestBody MemberRequest request) {
        MemberResponse member = memberService.createMember(request);
        return ResponseEntity.created(URI.create("/members/" + member.getId())).body(member);
    }

    @GetMapping("/me")
    public ResponseEntity<MemberResponse> findMe(@AuthenticationPrincipal LoginMember loginMember) {
        MemberResponse member = memberService.findMember(loginMember.getId());
        return ResponseEntity.ok().body(member);
    }

    @PostMapping("/address")
    public ResponseEntity<MemberResponse> changeAddress(@AuthenticationPrincipal LoginMember loginMember,
            @RequestBody Address address) {
        return ResponseEntity.ok(memberService.changeAddress(loginMember.getId(), address));
    }
}
