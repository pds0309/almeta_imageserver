package com.pds0309.almeta_imageserver.vo;

import com.pds0309.almeta_imageserver.exception.ExceptionMessages;
import com.pds0309.almeta_imageserver.exception.UserRequestException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {
    ALGO_POST("algo"), COMMUNITY_POST("community");

    private final String name;

    public static String getNameByValueOf(String name) {
        try {
            return Category.valueOf(name).getName();
        } catch (IllegalArgumentException e) {
            throw new UserRequestException(ExceptionMessages.INVALID_REQUEST);
        }
    }

}
