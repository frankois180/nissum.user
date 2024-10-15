package com.nissum.user.infrastructure.advice;

import com.nissum.user.domain.exception.InvalidEmailException;
import com.nissum.user.domain.exception.RecordExistException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@NoArgsConstructor
@ControllerAdvice
public class GeneralAdviceController {

    private static final List<ErrorDescriptor> ERROR_CATALOG = new ArrayList<>();

    static {
        ERROR_CATALOG.add(new ErrorDescriptor(InvalidEmailException.class, HttpStatus.CONFLICT, HttpStatus.CONFLICT.toString()));
        ERROR_CATALOG.add(new ErrorDescriptor(RecordExistException.class, HttpStatus.CONFLICT, HttpStatus.CONFLICT.toString()));
        ERROR_CATALOG.add(new ErrorDescriptor(UnexpectedException.class, HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.toString()));

    }

    @ExceptionHandler({
            Exception.class,
            RecordExistException.class,
            InvalidEmailException.class
    })
    public final ResponseEntity<?> handleAllExceptions(Exception exception) {

        log.error(exception.getMessage(), exception);

        ErrorDescriptor errorDesc = findDescriptorByException(exception);

        return ResponseEntity.status(errorDesc.getHttpStatus())
                .body(NotificationErrorResponse
                        .builder().message(exception.getMessage()).build());

    }

    private ErrorDescriptor findDescriptorByException(Exception ex) {

        return ERROR_CATALOG.stream()
                .filter(errorDes -> errorDes.getExType().equals(ex.getClass()))
                .findFirst()
                .orElse(new ErrorDescriptor(
                        null, HttpStatus.INTERNAL_SERVER_ERROR,
                        HttpStatus.INTERNAL_SERVER_ERROR.toString()));

    }

    @Getter
    private static class ErrorDescriptor {

        Class<? extends Throwable> exType;
        HttpStatus httpStatus;
        String responseCode;

        private ErrorDescriptor(Class<? extends Throwable> exType,
                                HttpStatus httpStatus, String responseCode) {

            this.exType = exType;
            this.httpStatus = httpStatus;
            this.responseCode = responseCode;

        }

    }
}