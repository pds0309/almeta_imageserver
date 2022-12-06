package com.pds0309.almeta_imageserver.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ErrorResponse {
    String message;
    String errorType;

    public ErrorResponse(String message, CustomErrorType errorType) {
        this.message = message;
        this.errorType = errorType.getName();
    }

}
