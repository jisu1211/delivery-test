package delivery.auth.exception;

public enum AuthExceptionCode {
    INVALID_TOKEN("The token entered is not a valid token.");

    private static final String TITLE = "[ERROR] ";
    private String message;

    AuthExceptionCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return TITLE + message;
    }
}
