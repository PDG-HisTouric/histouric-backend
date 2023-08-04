package com.pdg.histouric.error;

import com.pdg.histouric.constant.PermissionErrorCode;
import com.pdg.histouric.constant.RoleErrorCode;
import com.pdg.histouric.constant.UserErrorCode;
import com.pdg.histouric.error.exception.*;
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
    public ResponseEntity<PermissionError> handlePermissionException(PermissionException permissionException) {
        return new ResponseEntity<>(permissionException.getError(), permissionException.getHttpStatus());
    }

//    @ExceptionHandler
//    public ResponseEntity<PermissionError> handlePermissionException(MethodArgumentNotValidException methodArgumentNotValidException) {
//        PermissionException permissionException = new PermissionException(
//                HttpStatus.BAD_REQUEST,
//                new PermissionError(PermissionErrorCode.UNIVERSAL_ANNOTATION_CODE,
//                        Objects.requireNonNull(methodArgumentNotValidException.getFieldError()).getDefaultMessage())
//        );
//        return new ResponseEntity<>(permissionException.getError(), permissionException.getHttpStatus());
//    }

    @ExceptionHandler
    public ResponseEntity<RoleError> handleRoleException(RoleException roleException) {
        return new ResponseEntity<>(roleException.getError(), roleException.getHttpStatus());
    }

//    @ExceptionHandler
//    public ResponseEntity<RoleError> handleRoleException(MethodArgumentNotValidException methodArgumentNotValidException) {
//        RoleException roleException = new RoleException(
//                HttpStatus.BAD_REQUEST,
//                new RoleError(RoleErrorCode.UNIVERSAL_ANNOTATION_CODE,
//                        Objects.requireNonNull(methodArgumentNotValidException.getFieldError()).getDefaultMessage())
//        );
//        return new ResponseEntity<>(roleException.getError(), roleException.getHttpStatus());
//    }
}
