package com.pds0309.almeta_imageserver.service;

import com.pds0309.almeta_imageserver.config.ImageProperties;
import com.pds0309.almeta_imageserver.dto.PostImageResponse;
import com.pds0309.almeta_imageserver.dto.UploadRequest;
import com.pds0309.almeta_imageserver.utils.DateHandler;
import com.pds0309.almeta_imageserver.utils.FileHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

import static com.pds0309.almeta_imageserver.exception.ExceptionMessages.CANNOT_READ_FILE;

@Service
@RequiredArgsConstructor
public class BoardImageService implements ImageService {

    private final FileHandler fileHandler;
    private final DateHandler dateHandler;

    private final ImageProperties imageProperties;

    @Override
    public PostImageResponse saveImage(MultipartFile file, UploadRequest uploadRequest) {
        String fileName = fileHandler.getFileName(file, dateHandler.getCurrentTime());
        String directoryTree = genDirectoryTreeByRequest(uploadRequest, dateHandler.getCurrentDate());

        try {
            Path targetPath = fileHandler.genTargetPath(directoryTree, fileName, imageProperties.getRealDirectory());
            fileHandler.transferOriginFileToTargetPath(file, targetPath);
        } catch (IOException e) {
            throw new IllegalArgumentException(CANNOT_READ_FILE);
        }
        return new PostImageResponse(
                imageProperties.getPresentationDirectory(),
                directoryTree,
                fileName);
    }

    public String genDirectoryTreeByRequest(UploadRequest uploadRequest, String currentDate) {
        return uploadRequest.getUploader() + "/" +
                uploadRequest.getCategory() + "/" +
                currentDate;
    }

}
