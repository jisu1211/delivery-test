package delivery.delivery.exception;

public enum DeliveryExceptionCode {
    EXCEEDED_SEARCH_DATE("Delivery searching is only available for data within 3 days.");

    private static final String TITLE = "[ERROR] ";

    private String message;

    DeliveryExceptionCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return TITLE + message;
    }
}
