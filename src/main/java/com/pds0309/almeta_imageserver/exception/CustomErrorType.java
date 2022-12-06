package com.pds0309.almeta_imageserver.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomErrorType {
    FILE_REQUEST_ERROR("파일 핸들링 에러"),
    USER_REQUEST_ERROR("요청 에러"),
    INTERNAL_SERVER_ERROR("서버 에러");

    private final String name;

}
