package com.pds0309.almeta_imageserver.vo;

import com.pds0309.almeta_imageserver.exception.UserRequestException;
import org.springframework.util.StringUtils;

import java.util.Arrays;

public enum ContentType {
    JPG, JPEG, PNG, GIF;

    public static boolean checkValid(String contentType) {
        if (!StringUtils.hasText(contentType)) {
            return false;
        }
        try {
            String type = contentType.replaceFirst("(?i)image/", "");
            ContentType.valueOf(type.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            throw new UserRequestException("파일 형식은 " + Arrays.toString(ContentType.values()) + " 중 하나여야 합니다.");
        }
    }

}
