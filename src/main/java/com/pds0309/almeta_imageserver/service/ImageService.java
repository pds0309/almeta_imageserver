package com.pds0309.almeta_imageserver.service;

import com.pds0309.almeta_imageserver.dto.PostImageResponse;
import com.pds0309.almeta_imageserver.dto.UploadRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    PostImageResponse saveImage(final MultipartFile file, final UploadRequest uploadRequest) throws IOException;

}
