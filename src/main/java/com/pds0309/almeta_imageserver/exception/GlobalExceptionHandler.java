package com.pds0309.almeta_imageserver.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(final RuntimeException e) {
        log.info(e.getMessage());
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ErrorResponse(e.getMessage(), CustomErrorType.FILE_REQUEST_ERROR));
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({UserRequestException.class})
    public ResponseEntity<ErrorResponse> handleUserRequestException(final UserRequestException e) {
        log.info(e.getMessage());
        return ResponseEntity.status(BAD_REQUEST)
                .body(new ErrorResponse(e.getMessage(), CustomErrorType.USER_REQUEST_ERROR));
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleIllegalArgumentException(final Exception e) {
        log.info(e.getMessage(), e);
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body("이미지 서버 내부 오류");
    }

}
