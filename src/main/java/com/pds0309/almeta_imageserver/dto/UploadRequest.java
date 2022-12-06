package com.pds0309.almeta_imageserver.dto;

import com.pds0309.almeta_imageserver.exception.UserRequestException;
import com.pds0309.almeta_imageserver.vo.Category;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UploadRequest {

    private static final String INVALID_UPLOADER_MESSAGE = "업로더 정보가 올바르지 않습니다";
    @NotNull(message = INVALID_UPLOADER_MESSAGE)
    @Positive(message = INVALID_UPLOADER_MESSAGE)
    @Digits(fraction = 0, integer = 30, message = INVALID_UPLOADER_MESSAGE)
    Long uploader;

    @NotBlank(message = "카테고리를 입력하세요")
    String category;

    public UploadRequest(Long uploader, String category) {
        this.uploader = uploader;
        this.category = Category.getNameByValueOf(category);
    }

}
