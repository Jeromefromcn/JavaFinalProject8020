package hk.edu.hkmu.jiang.javafinal.interfaces.exception;

import hk.edu.hkmu.jiang.javafinal.domain.shaired.exception.DomainException;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle a specific domain exception
    @ExceptionHandler(DomainException.class)
    public ResponseEntity<@NotNull BaseResponse<String>> handleDomainException(DomainException e) {
        log.error("DOMAIN_ERROR", e);
        BaseResponse<String> error = new BaseResponse<>("DOMAIN_ERROR", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // Handle data validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<@NotNull BaseResponse<String>> handleValidationException(MethodArgumentNotValidException e) {
        log.error("VALIDATION_ERROR", e);
        String errorMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        BaseResponse<String> error = new BaseResponse<>("VALIDATION_ERROR", errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // Handle all other exceptions as a fallback
    @ExceptionHandler(Exception.class)
    public ResponseEntity<@NotNull BaseResponse<String>> handleGeneralException(Exception e) {
        log.error("INTERNAL_ERROR", e);
        BaseResponse<String> error = new BaseResponse<>(
                "INTERNAL_ERROR", "An internal server error occurred");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}