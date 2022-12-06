package com.pds0309.almeta_imageserver.testUtils;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class TestImageFactory {

    private TestImageFactory() {

    }

    public static MultipartFile ofJPGSample(String name) throws IOException {
        BufferedImage image = getImage();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", out);
        } catch (IOException e) {
            return null;
        }
        byte[] bytes = out.toByteArray();
        return new CustomMultipartFile(bytes, name, name + ".jpg", "image/jpg", false, bytes.length);
    }

    private static BufferedImage getImage() throws IOException {
        File file = new File("src/test/resources/assets/linkedin-sales-solutions-zn2aUVfbUrk-unsplash.jpg");
        return ImageIO.read(file);
    }

}