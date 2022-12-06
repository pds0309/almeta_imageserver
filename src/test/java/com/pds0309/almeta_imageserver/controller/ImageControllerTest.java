package com.pds0309.almeta_imageserver.controller;

import com.pds0309.almeta_imageserver.dto.PostImageResponse;
import com.pds0309.almeta_imageserver.dto.UploadRequest;
import com.pds0309.almeta_imageserver.exception.UserRequestException;
import com.pds0309.almeta_imageserver.service.ImageService;
import com.pds0309.almeta_imageserver.vo.Category;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ImageController.class)
class ImageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ImageService imageService;

    @Autowired
    WebApplicationContext ctx;


    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .alwaysDo(print())
                .build();
    }

    private static final String POST_URL = "/api/v1/images";
    private static final UploadRequest validUploadRequest =
            new UploadRequest(1L, "ALGO_POST");

    private static final PostImageResponse response =
            new PostImageResponse("/abc", "/dir", "image.jpg");

    private static final MockMultipartFile image = new MockMultipartFile(
            "image",
            "image.jpg",
            MediaType.IMAGE_JPEG_VALUE,
            "Hell World!".getBytes()
    );

    @Test
    @DisplayName("정상적인 파일 요청에 대해 성공 응답 :200")
    void shouldValidRequestToRespondOk() throws Exception {

        given(imageService.saveImage(image, validUploadRequest))
                .willReturn(response);

        mockMvc.perform(multipart(POST_URL)
                        .file(image)
                        .flashAttr("upload", validUploadRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.imagePath").exists());
    }

    private static Stream<UploadRequest> invalidUploadRequests() {
        return Stream.of(
                new UploadRequest(0L, Category.ALGO_POST.name()),
                new UploadRequest(-1L, Category.ALGO_POST.name())
        );
    }

    @ParameterizedTest
    @MethodSource("invalidUploadRequests")
    @DisplayName("비정상적인 model attribute 요청 데이터에 대해 예외를 응답 :400")
    void shouldInvalidFileRequestToRespondBadRequest(UploadRequest request) throws Exception {
        mockMvc.perform(multipart(POST_URL)
                        .file(image)
                        .flashAttr("upload", request))
                .andExpect(status().isBadRequest())
                .andExpect(result ->
                        assertThat(result.getResolvedException()).
                                isInstanceOf(UserRequestException.class));
    }

}
