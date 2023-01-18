package member.domain;

import member.exception.MemberExceptionCode;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Password password;

    @Column(nullable = false)
    private String name;

    protected Member() {}

    public Member(String password, String name) {
        validate(name);

        this.password = new Password(password);
        this.name = name;
    }

    private void validate(String name) {
        if (!StringUtils.hasText(name)) {
            throw new IllegalArgumentException(MemberExceptionCode.REQUIRED_NAME.getMessage());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Member member = (Member) o;
        return Objects.equals(id, member.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
