package member.domain;

import member.exception.MemberExceptionCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("회원 클래스 테스트")
class MemberTest {

    @DisplayName("회원 객체 생성시 회원명이 null이거나 비어있을 수 없다.")
    @ParameterizedTest
    @NullAndEmptySource
    void newMember(String name) {
        assertThatThrownBy(() -> {
            new Member("acinEo5nL7cie1", name);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MemberExceptionCode.REQUIRED_NAME.getMessage());
    }
}
