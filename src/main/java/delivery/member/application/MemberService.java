package delivery.member.application;

import delivery.exception.EntityNotFoundException;
import delivery.exception.EntityNotFoundExceptionCode;
import delivery.member.domain.Address;
import delivery.member.domain.Member;
import delivery.member.domain.MemberRepository;
import delivery.member.dto.MemberRequest;
import delivery.member.dto.MemberResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public MemberResponse createMember(MemberRequest request) {
        Member member = memberRepository.save(request.toMember());
        return MemberResponse.of(member);
    }

    @Transactional(readOnly = true)
    public MemberResponse findMember(Long id) {
        Member member = findById(id);
        return MemberResponse.of(member);
    }

    private Member findById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(EntityNotFoundExceptionCode.NOT_FOUND_BY_ID));
    }

    @Transactional
    public MemberResponse changeAddress(Long memberId, Address address) {
        Member member = findById(memberId);
        member.changeAddress(address);
        return MemberResponse.of(memberRepository.save(member));
    }
}
