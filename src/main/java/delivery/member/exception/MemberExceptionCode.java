package delivery.member.exception;

public enum MemberExceptionCode {
    REQUIRED_EMAIL("The email is a required field."),
    REQUIRED_NAME("The name is a required field"),
    REQUIRED_PASSWORD("The password is a required field."),
    INVALID_EMAIL("The input string is not in email format."),
    INVALID_PASSWORD("The input string is not in password format."),
    PASSWORD_NOT_MATCH("The password entered does not match the member's password."),
    NOT_FOUND_BY_EMAIL("the member not found by email.");

    private static final String TITLE = "[ERROR] ";
    private String message;

    MemberExceptionCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return TITLE + message;
    }
}
