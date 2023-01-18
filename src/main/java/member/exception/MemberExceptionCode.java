package member.exception;

public enum MemberExceptionCode {
    REQUIRED_NAME("The name is a required field"),
    REQUIRED_PASSWORD("The password is a required field."),
    INVALID_PASSWORD("The input string is not in password format.");

    private static final String TITLE = "[ERROR] ";
    private String message;

    MemberExceptionCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return TITLE + message;
    }
}
