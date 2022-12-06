package com.pds0309.almeta_imageserver.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionMessages {

    public static final String FILE_NOT_FOUND = "파일이 존재하지 않습니다.";
    public static final String FILE_NAME_NOT_FOUND = "파일 이름이 없습니다.";
    public static final String CONTENT_TYPE_NOT_FOUND = "파일 타입이 없습니다.";
    public static final String INVALID_FILE_NAME = "이름에 부적합한 문자가 포함되어있습니다";
    public static final String TOO_LONG_FILE_NAME = "이름 길이가 100을 초과하지 않아야 합니다";
    public static final String CANNOT_READ_FILE = "요청한 파일을 읽을 수 없습니다.";
    public static final String EXCEED_SIZE = "파일 용량은 8MB 이하여야 합니다.";
    public static final String INVALID_REQUEST = "부적절한 요청 포맷입니다.";

}
