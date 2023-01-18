package delivery.member.domain;

import delivery.member.exception.MemberExceptionCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("회원 클래스 테스트")
class MemberTest {

    @DisplayName("회원 객체 생성시 회원명이 null이거나 비어있을 수 없다.")
    @ParameterizedTest
    @NullAndEmptySource
    void newMember(String name) {
        assertThatThrownBy(() -> {
            new Member("test@test.com", "acinEo5nL7cie1", name);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(MemberExceptionCode.REQUIRED_NAME.getMessage());
    }

    @DisplayName("주소를 변경한다.")
    @Test
    void changeAddress() {
        // given
        Member member = new Member("test@test.com", "acinEo5nL7cie1", "member1");
        member.changeAddress(new Address("서울 강남구", "테헤란로", "06000"));

        // when
        member.changeAddress(new Address("서울 강남구", "테헤란로 11", "06001"));

        // then
        assertAll(
                () -> assertEquals("서울 강남구", member.getAddress().getCity()),
                () -> assertEquals("테헤란로 11", member.getAddress().getStreet()),
                () -> assertEquals("06001", member.getAddress().getZipcode())
        );
    }
}
