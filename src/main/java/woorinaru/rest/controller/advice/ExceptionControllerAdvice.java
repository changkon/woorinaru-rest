package woorinaru.rest.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import woorinaru.core.exception.ReferenceNotFoundException;
import woorinaru.core.exception.ResourceNotFoundException;
import woorinaru.rest.dto.exception.WoorinaruErrorResponse;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<WoorinaruErrorResponse> handleException(ResourceNotFoundException exception) {
        WoorinaruErrorResponse response = new WoorinaruErrorResponse();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(exception.getMessage());
        response.setTimeStamp(System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler
    public ResponseEntity<WoorinaruErrorResponse> handleException(ReferenceNotFoundException exception) {
        WoorinaruErrorResponse response = new WoorinaruErrorResponse();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(exception.getMessage());
        response.setTimeStamp(System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Create a generic exception
    @ExceptionHandler
    public ResponseEntity<WoorinaruErrorResponse> handleException(Exception exception) {
        WoorinaruErrorResponse response = new WoorinaruErrorResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(exception.getMessage());
        response.setTimeStamp(System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
