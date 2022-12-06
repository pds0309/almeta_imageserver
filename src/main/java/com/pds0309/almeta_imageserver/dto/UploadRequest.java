package com.pds0309.almeta_imageserver.dto;

import com.pds0309.almeta_imageserver.vo.Category;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UploadRequest {
    @NotNull
    @Positive
    @Digits(fraction = 0, integer = 30)
    Long uploader;

    @NotBlank
    String category;

    public UploadRequest(Long uploader, String category) {
        this.uploader = uploader;
        this.category = Category.getNameByValueOf(category);
    }

}
