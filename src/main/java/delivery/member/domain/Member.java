package delivery.member.domain;

import delivery.common.BaseEntity;
import delivery.member.exception.MemberExceptionCode;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Email email;

    @Embedded
    private Password password;

    @Column(nullable = false)
    private String name;

    @Embedded
    private Address address;

    protected Member() {}

    public Member(String email, String password, String name) {
        validate(name);

        this.email = new Email(email);
        this.password = new Password(password);
        this.name = name;
    }

    private void validate(String name) {
        if (!StringUtils.hasText(name)) {
            throw new IllegalArgumentException(MemberExceptionCode.REQUIRED_NAME.getMessage());
        }
    }

    public void checkPassword(String password) {
        this.password.checkPassword(password);
    }

    public void changeAddress(Address address) {
        if (Objects.isNull(this.address) || !this.address.equals(address)) {
            this.address = new Address(address.getCity(), address.getStreet(), address.getZipcode());
        }
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return this.email.getEmail();
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
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
        return Objects.equals(id, member.id)
                && Objects.equals(email, member.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
