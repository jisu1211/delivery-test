package delivery.member.domain;

import delivery.exception.AuthorizationException;
import delivery.member.exception.MemberExceptionCode;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.regex.Pattern;

@Embeddable
public class Password {
    private static final Pattern REGEX =
            Pattern.compile("^(?:(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])|(?=.*\\d)(?=.*[^A-Za-z0-9])(?=.*[a-z])"
                    + "|(?=.*[^A-Za-z0-9])(?=.*[A-Z])(?=.*[a-z])|(?=.*\\d)(?=.*[A-Z])(?=.*[^A-Za-z0-9]))(?!.*(.)\\1{2,})"
                    + "[A-Za-z0-9#?!@$%^&*-]{12,}$");

    @Column(nullable = false)
    private String password;

    protected Password() {}

    public Password(String password) {
        validate(password);
        this.password = password;
    }

    private void validate(String password) {
        if (!StringUtils.hasText(password)) {
            throw new IllegalArgumentException(MemberExceptionCode.REQUIRED_PASSWORD.getMessage());
        }

        if (!Pattern.matches(REGEX.pattern(), password)) {
            throw new IllegalArgumentException(MemberExceptionCode.INVALID_PASSWORD.getMessage());
        }
    }

    public void checkPassword(String password) {
        if (!equals(new Password(password))) {
            throw new AuthorizationException(MemberExceptionCode.PASSWORD_NOT_MATCH.getMessage());
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

        Password password1 = (Password) o;
        return Objects.equals(password, password1.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password);
    }
}
