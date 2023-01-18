package delivery.member.domain;

import delivery.member.domain.Email;
import delivery.member.exception.MemberExceptionCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("회원 이메일 관리 클래스 테스트")
class EmailTest {
    @DisplayName("이메일은 null이거나 빈 문자열 일 수 없다.")
    @ParameterizedTest
    @NullAndEmptySource
    void validate(String email) {
        assertThatThrownBy(() -> {
            new Email(email);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MemberExceptionCode.REQUIRED_EMAIL.getMessage());
    }

    @DisplayName("잘못된 형식의 이메일 생성 테스트")
    @ParameterizedTest
    @ValueSource(strings = { "test", "testuser@", "testuser@test", "testuser#!@test.com", "testuser@test#.com" })
    void checkPattern(String email) {
        assertThatThrownBy(() -> {
            new Email(email);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MemberExceptionCode.INVALID_EMAIL.getMessage());
    }

    @DisplayName("올바른 이메일 형식으로 생성 테스트")
    @ParameterizedTest
    @ValueSource(strings = { "testuser@test.com", "test_user12@test.com", "test-user12@test.co.kr"})
    void Email_객체_생성(String email) {
        assertDoesNotThrow(() -> {
            new Email(email);
        });
    }
}
