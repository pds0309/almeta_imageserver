package com.pds0309.almeta_imageserver.config;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(classes = {PropertiesConfiguration.class})
class ImagePropertiesTest {

    @Autowired
    ImageProperties imageProperties;
    private static final String REAL_DIR = "src/test/resources/static/images/";
    private static final String PRESENT_DIR = "/images/";
    @Test
    @DisplayName("이미지 프로퍼티를 가져올 수 있다.")
    void shouldGetImageProperties() {
        assertThat(imageProperties.getRealDirectory()).isEqualTo(REAL_DIR);
        assertThat(imageProperties.getPresentationDirectory()).isEqualTo(PRESENT_DIR);
    }

}
