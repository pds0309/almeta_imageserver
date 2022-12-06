package com.pds0309.almeta_imageserver.dto;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class UploadRequestTest {
    private static Validator validator;
    private static ValidatorFactory factory;

    @BeforeAll
    public static void setup() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private static Stream<UploadRequest> InvalidUploadRequests() {
        return Stream.of(
                new UploadRequest(0L, "ALGO_POST"),
                new UploadRequest(-1L, "ALGO_POST"),
                new UploadRequest(null, "ALGO_POST"));
    }

    private static Stream<UploadRequest> ValidUploadRequests() {
        return Stream.of(
                new UploadRequest(15L, "ALGO_POST"),
                new UploadRequest(44_651_617_210L, "ALGO_POST"));
    }

    @ParameterizedTest
    @MethodSource("InvalidUploadRequests")
    @DisplayName("부적절한 UploadRequest 데이터는 Constraint를 위반한다.")
    void shouldInvalidUploadRequestViolateConstraints(UploadRequest uploadRequest) {
        assertThat(validator.validate(uploadRequest)).isNotEmpty();
    }

    @ParameterizedTest
    @MethodSource("ValidUploadRequests")
    @DisplayName("적절한 UploadRequest 데이터는 적절하다")
    void shouldValidUploadRequestIsOk(UploadRequest uploadRequest) {
        assertThat(validator.validate(uploadRequest)).isEmpty();
    }

    @AfterAll
    static void close() {
        factory.close();
    }

}
