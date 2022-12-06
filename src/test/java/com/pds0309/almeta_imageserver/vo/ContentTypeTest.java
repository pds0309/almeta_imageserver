package com.pds0309.almeta_imageserver.vo;

import com.pds0309.almeta_imageserver.exception.UserRequestException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class ContentTypeTest {

    private static Stream<String> invalidTypes() {
        return Stream.of("MP4", "text/plain", "image/bmp");
    }

    private static Stream<String> validTypes() {
        return Arrays.stream(ContentType.values()).map(type -> "image/" + type);
    }

    private static Stream<String> blankTypes() {
        return Stream.of("", " ", null);
    }

    @ParameterizedTest
    @MethodSource("validTypes")
    @DisplayName("지정된 타입에 대해 검증이 성공한다.")
    void shouldValidTypeValidationReturnTrue(String type) {
        assertThat(ContentType.checkValid(type)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("invalidTypes")
    @DisplayName("지정되지 않은 잘못된 타입에 대해 검증에 대해 예외를 발생시킨다.")
    void shouldInvalidTypeValidationThrowsException(String type) {
        assertThatThrownBy(() -> ContentType.checkValid(type))
                .isInstanceOf(UserRequestException.class);
    }

    @ParameterizedTest
    @MethodSource("blankTypes")
    @DisplayName("타입이 지정되지 않았을 경우 검증에 실패한다.")
    void shouldBlankTypeValidationReturnFalse(String type) {
        assertThat(ContentType.checkValid(type)).isFalse();
    }

}
