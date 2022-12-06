package com.pds0309.almeta_imageserver.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostImageResponse {
    String imagePath;

    public PostImageResponse(String baseUrl, String directoryTree, String imageName) {
        this.imagePath = baseUrl + directoryTree + "/" + imageName;
    }

}
