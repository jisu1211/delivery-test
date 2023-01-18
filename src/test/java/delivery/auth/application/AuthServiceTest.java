package delivery.auth.application;

import delivery.auth.application.AuthService;
import delivery.auth.dto.TokenRequest;
import delivery.auth.dto.TokenResponse;
import delivery.auth.token.JwtTokenProvider;
import delivery.member.domain.Member;
import delivery.member.domain.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@DisplayName("로그인 테스트")
@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
    private static final String EMAIL = "test@test.com";
    private static final String PASSWORD = "acinEo5nL7cie1";
    private static final String NAME = "member1";
    private static final String TOKEN = "TOKEN";

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private AuthService authService;

    @Test
    void login() {
        when(memberRepository.findByEmail(anyString())).thenReturn(Optional.of(new Member(EMAIL, PASSWORD, NAME)));
        when(jwtTokenProvider.createToken(anyString())).thenReturn(TOKEN);

        TokenResponse tokenResponse = authService.login(new TokenRequest(EMAIL, PASSWORD));

        assertThat(tokenResponse.getAccessToken()).isNotBlank();
    }
}
