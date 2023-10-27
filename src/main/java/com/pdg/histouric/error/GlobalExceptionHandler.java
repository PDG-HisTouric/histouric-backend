package com.pdg.histouric.error;

import com.pdg.histouric.constant.UserErrorCode;
import com.pdg.histouric.error.exception.*;
import jakarta.persistence.PersistenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<UserError> handleException(UserException userException) {
        return new ResponseEntity<>(userException.getError(), userException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<UserError> handleException(MethodArgumentNotValidException methodArgumentNotValidException) {
        UserException userException = new UserException(
                HttpStatus.BAD_REQUEST,
                new UserError(UserErrorCode.UNIVERSAL_ANNOTATION_CODE,
                Objects.requireNonNull(methodArgumentNotValidException.getFieldError()).getDefaultMessage())
        );
        return new ResponseEntity<>(userException.getError(), userException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<RoleError> handleRoleException(RoleException roleException) {
        return new ResponseEntity<>(roleException.getError(), roleException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<BicError> handleBicException(BicException bicException) {
        return new ResponseEntity<>(bicException.getError(), bicException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<HistoryError> handleHistoryException(HistoryException historyException) {
        return new ResponseEntity<>(historyException.getError(), historyException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<JPAError> handlePersistenceException(PersistenceException persistenceException) {
        JPAError jpaError = new JPAError(HttpStatus.BAD_REQUEST, persistenceException.getMessage());
        return new ResponseEntity<>(jpaError, HttpStatus.BAD_REQUEST);
    }
}
