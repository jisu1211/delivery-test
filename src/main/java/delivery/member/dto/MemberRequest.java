package delivery.member.dto;

import delivery.member.domain.Member;

public class MemberRequest {
    private String email;
    private String password;
    private String name;

    public MemberRequest() {}

    public MemberRequest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public Member toMember() {
        return new Member(email, password, name);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
