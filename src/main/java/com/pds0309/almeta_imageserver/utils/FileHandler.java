package com.pds0309.almeta_imageserver.utils;


import com.pds0309.almeta_imageserver.vo.ContentType;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.pds0309.almeta_imageserver.exception.ExceptionMessages.*;

@Component
public class FileHandler {

    public String getFileName(MultipartFile file, String currentTime) {
        validFile(file);
        String fileName = StringUtils.cleanPath(currentTime + "-" + file.getOriginalFilename());
        validFilename(fileName);
        return fileName;
    }

    public Path genTargetPath(String directoryTree, String fileName, String realRootDirectory) throws IOException {
        Path directory = getTargetDirectory(directoryTree, realRootDirectory);
        createDirectory(directory);
        return directory.resolve(fileName).normalize();
    }

    public void transferOriginFileToTargetPath(MultipartFile file, Path targetPath) throws IOException {
        file.transferTo(targetPath);
    }

    private void validFile(MultipartFile file) {
        Assert.notNull(file, FILE_NOT_FOUND);
        Assert.state(StringUtils.hasText(file.getOriginalFilename()), FILE_NAME_NOT_FOUND);
        Assert.notNull(file.getContentType(), CONTENT_TYPE_NOT_FOUND);
        Assert.state(file.getSize() < 10_485_750, EXCEED_SIZE);
        ContentType.checkValid(file.getContentType());
    }

    private void validFilename(final String fileName) {
        Assert.state(!fileName.contains(".."), INVALID_FILE_NAME);
        Assert.state(fileName.length() < 100, TOO_LONG_FILE_NAME);
    }

    private Path getTargetDirectory(String directoryTree, String getRealRootDirectory) {
        return Paths.get(getRealRootDirectory + directoryTree).toAbsolutePath().normalize();
    }

    private void createDirectory(Path directory) throws IOException {
        Files.createDirectories(directory);
    }

}
