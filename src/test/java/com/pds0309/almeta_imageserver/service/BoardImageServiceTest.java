package com.pds0309.almeta_imageserver.service;

import com.pds0309.almeta_imageserver.config.ImageProperties;
import com.pds0309.almeta_imageserver.dto.PostImageResponse;
import com.pds0309.almeta_imageserver.dto.UploadRequest;
import com.pds0309.almeta_imageserver.testUtils.MultipartFiles;
import com.pds0309.almeta_imageserver.utils.DateHandler;
import com.pds0309.almeta_imageserver.utils.FileHandler;
import com.pds0309.almeta_imageserver.vo.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import static com.pds0309.almeta_imageserver.utils.DateFormat.DATE_FORMATTER;
import static com.pds0309.almeta_imageserver.utils.DateFormat.TIME_FORMATTER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest(classes = {BoardImageService.class, FileHandler.class})
class BoardImageServiceTest {

    @MockBean
    private ImageProperties imageProperties;
    @MockBean
    private DateHandler dateHandler;

    @Autowired
    private FileHandler fileHandler;

    @Autowired
    private BoardImageService boardImageService;

    private final String REAL_DIR = "src/test/resources/images/";
    private final String PRES_DIR = "/images/";
    private final String IMAGE_NAME = "test-image";

    private final UploadRequest uploadRequest = new UploadRequest(1L, Category.ALGO_POST.name());

    private final static String CURRENT_DATE = LocalDate.of(2019, 10, 4).format(DATE_FORMATTER);
    private final static String CURRENT_TIME = LocalTime.of(10, 4, 6).format(TIME_FORMATTER);

    @BeforeEach
    void beforeEach() {
        given(imageProperties.getRealDirectory()).willReturn(REAL_DIR);
        given(imageProperties.getPresentationDirectory()).willReturn(PRES_DIR);
        given(dateHandler.getCurrentDate()).willReturn(CURRENT_DATE);
        given(dateHandler.getCurrentTime()).willReturn(CURRENT_TIME);
    }

    @Test
    void validImagePostsShouldSaveInDirectory() throws IOException {
        MultipartFile image = MultipartFiles.ofValid(IMAGE_NAME);
        PostImageResponse imagePostResponse = boardImageService.saveImage(image, uploadRequest);
        assertThat(imagePostResponse).isNotNull();
        assertThat(imagePostResponse.getImagePath())
                .isEqualTo("/images/1/algo/19-10-04/10-04-06-test-image.jpg");
    }

}
