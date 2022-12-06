package com.pds0309.almeta_imageserver.vo;

import com.pds0309.almeta_imageserver.exception.UserRequestException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class CategoryTest {

    private static Stream<String> invalidCategories() {
        return Stream.of(" ", "hello", "");
    }

    private static Stream<Arguments> validCategories() {
        return Arrays.stream(Category.values())
                .map(category -> Arguments.of(category.name(), category.getName()));
    }

    @ParameterizedTest
    @MethodSource("invalidCategories")
    @DisplayName("부적절한 카테고리 문자에 대한 값 요청에 대해 예외를 발생한다.")
    void shouldInvalidCategoriesRequestThrowsException(String categories) {
        assertThatThrownBy(() -> Category.getNameByValueOf(categories))
                .isInstanceOf(UserRequestException.class);
    }

    @ParameterizedTest
    @MethodSource(value = "validCategories")
    @DisplayName("적절한 카테고리 문자에 대한 값 요청에 대해 name 값을 얻어올 수 있다.")
    void shouldValidCategoriesRequestReturnName(String categories, String name) {
        assertThat(Category.getNameByValueOf(categories)).isEqualTo(name);
    }

}
