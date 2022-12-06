package com.pds0309.almeta_imageserver.utils;

import com.pds0309.almeta_imageserver.testUtils.MultipartFiles;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest(classes = {FileHandler.class})
class FileHandlerTest {

    @Autowired
    private FileHandler fileHandler;

    private static Stream<MultipartFile> invalidFiles() {
        return MultipartFiles.ofInvalids().stream();
    }

    @Test
    @DisplayName("정상적인 파일 요청에 대해 file name을 얻을 수 있다.")
    void shouldValidFileRequestReturnFilename() throws IOException {
        String name = "helo";
        assertThat(fileHandler.getFileName(MultipartFiles.ofValid(name), "date"))
                .isEqualTo("date-helo.jpg");
    }

    @ParameterizedTest
    @MethodSource("invalidFiles")
    @DisplayName("비정상적인 파일 요청에 대해 예외를 반환한다.")
    void shouldInvalidFileRequestThrowsException(MultipartFile file) throws IOException {
        assertThatExceptionOfType(Exception.class)
                .isThrownBy(() -> fileHandler.getFileName(file, "date"));
    }

    @Test
    @DisplayName("파일이 null일 경우 예외를 반환한다.")
    void shouldNullFileRequestThrowsException() throws IOException {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> fileHandler.getFileName(null, "date"));
    }

    private static final String REAL_ROOT = "src/test/resources/static/images/";

    @Test
    @DisplayName("파일에 대한 targetPath를 생성할 수 있다.")
    void shouldGenerateTargetPath() throws IOException {
        String directoryTree = "/abc/123";
        String fileName = "date-hello.jpg";
        assertThat(fileHandler.genTargetPath(directoryTree, fileName, REAL_ROOT))
                .isInstanceOfSatisfying(Path.class, path -> {
                    assertThat(path.normalize().toString()).isNotBlank();
                    assertThat(path.normalize()).endsWithRaw(Paths.get(fileName));
                });

    }

}
