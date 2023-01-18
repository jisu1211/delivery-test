package delivery.member.acceptance;

import delivery.AcceptanceTest;
import delivery.auth.acceptance.AuthAcceptance;
import delivery.auth.dto.TokenResponse;
import delivery.member.dto.MemberResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MemberAcceptanceTest extends AcceptanceTest {
    private static final String EMAIL = "test@test.com";
    private static final String PASSWORD = "password157#";
    private static final String NAME = "member1";

    /**
     * When 회원을 생성하면
     * Then 회원이 생성된다.
     */
    @Test
    void createMember() {
        // when
        MemberResponse memberResponse = MemberAcceptance.create_member(EMAIL, PASSWORD, NAME).as(MemberResponse.class);

        // then
        assertAll(
                () -> assertThat(memberResponse.getId()).isNotNull(),
                () -> assertThat(memberResponse.getEmail()).isEqualTo(EMAIL),
                () -> assertThat(memberResponse.getName()).isEqualTo(NAME)
        );
    }

    /**
     * Given 회원이 등록되어 있고
     * When 유효하지 않은 토큰 정보로 내 정보를 조회하면
     * Then 내 정보를 조회할 수 없다.
     */
    @DisplayName("유효하지 않은 토큰 정보로 내 정보를 조회한다.")
    @Test
    void selectMeWithInvalidToken() {
        // when
        TokenResponse invalidToken = new TokenResponse("Invalid_Token");
        ExtractableResponse<Response> response = MemberAcceptance.select_me(invalidToken);

        // then
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.statusCode());
    }

    /**
     * Given 회원이 등록되어 있고
     * When 내 정보를 발급된 토큰 정보와 함께 조회하면
     * Then 내 정보를 조회할 수 있다.
     */
    @DisplayName("발급된 토큰 정보와 함께 내 정보를 조회한다.")
    @Test
    void selectMe() {
        // given
        MemberAcceptance.create_member(EMAIL, PASSWORD, NAME);
        TokenResponse tokenResponse = AuthAcceptance.member_token_is_issued(EMAIL, PASSWORD);

        // when
        MemberResponse memberResponse = MemberAcceptance.select_me(tokenResponse).as(MemberResponse.class);

        // then
        assertEquals(EMAIL, memberResponse.getEmail());
    }
}
