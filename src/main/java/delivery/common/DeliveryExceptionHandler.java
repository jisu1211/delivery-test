package delivery.common;

import delivery.exception.AuthorizationException;
import delivery.exception.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DeliveryExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ AuthorizationException.class, EntityNotFoundException.class, IllegalArgumentException.class })
    protected ResponseEntity<Void> handle() {
        return ResponseEntity.badRequest().build();
    }
}
