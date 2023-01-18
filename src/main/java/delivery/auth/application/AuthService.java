package delivery.auth.application;

import delivery.auth.domain.LoginMember;
import delivery.auth.dto.TokenRequest;
import delivery.auth.dto.TokenResponse;
import delivery.auth.exception.AuthExceptionCode;
import delivery.auth.token.JwtTokenProvider;
import delivery.exception.AuthorizationException;
import delivery.member.domain.Member;
import delivery.member.domain.MemberRepository;
import delivery.member.exception.MemberExceptionCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthService {
    private MemberRepository memberRepository;
    private JwtTokenProvider jwtTokenProvider;

    public AuthService(MemberRepository memberRepository, JwtTokenProvider jwtTokenProvider) {
        this.memberRepository = memberRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public TokenResponse login(TokenRequest request) {
        Member member = findByEmail(request.getEmail());
        member.checkPassword(request.getPassword());

        String token = jwtTokenProvider.createToken(request.getEmail());
        return new TokenResponse(token);
    }

    private Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new AuthorizationException(MemberExceptionCode.NOT_FOUND_BY_EMAIL.getMessage()));
    }

    public LoginMember findMemberByToken(String credentials) {
        if (!jwtTokenProvider.validateToken(credentials)) {
            throw new AuthorizationException(AuthExceptionCode.INVALID_TOKEN.getMessage());
        }

        String email = jwtTokenProvider.getPayload(credentials);
        Member member = findByEmail(email);
        return new LoginMember(member.getId(), member.getEmail(), member.getName());
    }
}
