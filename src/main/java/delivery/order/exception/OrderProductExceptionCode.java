package delivery.order.exception;

public enum OrderProductExceptionCode {
    INVALID_QUANTITY("The quantity cannot be less than zero");

    private static final String TITLE = "[ERROR] ";

    private String message;

    OrderProductExceptionCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return TITLE + message;
    }
}
