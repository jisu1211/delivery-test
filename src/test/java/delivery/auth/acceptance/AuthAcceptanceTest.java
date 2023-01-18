package delivery.auth.acceptance;

import delivery.AcceptanceTest;
import delivery.member.acceptance.MemberAcceptance;
import delivery.member.dto.MemberResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("사용자 인증 관련 기능")
public class AuthAcceptanceTest extends AcceptanceTest {

    private static final String EMAIL = "testuser@test.com";
    private static final String PASSWORD = "password157#";
    private static final String NAME = "member1";

    @BeforeEach
    public void setUp() {
        super.setUp();

        MemberAcceptance.create_member(EMAIL, PASSWORD, NAME).as(MemberResponse.class);
    }

    /**
     * Given 회원을 생성하고
     * When 회원 정보로 로그인을 요청하면
     * Then 로그인 할 수 있다.
     */
    @DisplayName("회원 정보로 Bearer 인증 로그인을 한다.")
    @Test
    void myInfoWithBearerAuth() {
        // when
        ExtractableResponse<Response> response = AuthAcceptance.token_request(EMAIL, PASSWORD);

        // then
        assertEquals(HttpStatus.OK.value(), response.statusCode());
    }

    /**
     * Given 회원을 생성하고
     * When 다른 비밀번호로 로그인을 요청하면
     * Then 로그인 할 수 없다.
     */
    @DisplayName("다른 비밀번호로 Bearer 인증 로그인을 한다.")
    @Test
    void myInfoWithBadBearerAuth() {
        // when
        ExtractableResponse<Response> response = AuthAcceptance.token_request(EMAIL, "password369#");

        // then
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.statusCode());
    }
}
