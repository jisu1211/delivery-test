package member.domain;

import member.exception.MemberExceptionCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("회원 비밀번호 관리 클래스 테스트")
class PasswordTest {

    @DisplayName("비밀번호는 null이거나 비어있을 수 없다.")
    @ParameterizedTest
    @NullAndEmptySource
    void validate(String password) {
        assertThatThrownBy(() -> {
            new Password(password);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MemberExceptionCode.REQUIRED_PASSWORD.getMessage());
    }

    @DisplayName("비밀번호는 12글자 미만일 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = { "abd", "Fc1", "cdeT5&", "deiFc%", "rD-txR3&dVx" })
    void checkPattern(String password) {
        assertThatThrownBy(() -> {
            new Password(password);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MemberExceptionCode.INVALID_PASSWORD.getMessage());
    }

    @DisplayName("같은 문자가 3번 이상 반복될 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = { "acinEo5nL7cieee1", "acinEod$Lkcie###", "ACIneO555NL7CIe1", "ACINEODDD$L7CIE1#" })
    void checkPattern2(String password) {
        assertThatThrownBy(() -> {
            new Password(password);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MemberExceptionCode.INVALID_PASSWORD.getMessage());
    }

    @DisplayName("비밀번호는 영문자 대문자, 영문자 소문자, 숫자, 특수문자 중 3종류 미만을 포함할 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = { "acineodnlkcie", "acineo5nl7cie1", "ACINEODFLGCIEB", "ACINEOD$L(CIE-" })
    void checkPattern3(String password) {
        assertThatThrownBy(() -> {
            new Password(password);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MemberExceptionCode.INVALID_PASSWORD.getMessage());
    }

    @DisplayName("비밀번호는 영문자 대문자, 영문자 소문자, 숫자, 특수문자 중 3종류를 포함한 12글자 이상의 문자열이다.")
    @ParameterizedTest
    @ValueSource(strings = { "acinEo5nL7cie1", "acinEod$Lkcie#", "acineo5nl7cie1#$",
            "ACIneO5NL7CIe1", "ACiNEod$L-cIeF#", "ACINEOD$L7CIE1#" })
    void checkPattern4(String password) {
        assertDoesNotThrow(() -> {
          new Password(password);
        });
    }
}
