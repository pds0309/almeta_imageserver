package com.pds0309.almeta_imageserver.testUtils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public class MultipartFiles {

    private MultipartFiles() {

    }

    public static MultipartFile ofValid(String name) throws IOException {
        return TestImageFactory.ofJPGSample("hello");
    }

    public static List<MultipartFile> ofInvalids() {
        return List.of(
                new CustomMultipartFile(new byte[1], "name", "name.bmp", "image/bmp", false, 1),
                new CustomMultipartFile(new byte[1], "n..a..me", "n..a..me.gif", "image/gif", false, 15),
                new CustomMultipartFile(new byte[1], "", "", "image/jpg", false, 1),
                new CustomMultipartFile(new byte[0], "name", "name.jpeg", "image/jpeg", false, 10000000000L),
                new CustomMultipartFile(new byte[0], "012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789123456789123456789",
                        "012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789123456789123456789.jpeg", "image/jpeg", true, 0));
    }

}
