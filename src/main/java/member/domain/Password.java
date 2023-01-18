package member.domain;

import member.exception.MemberExceptionCode;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
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
}
