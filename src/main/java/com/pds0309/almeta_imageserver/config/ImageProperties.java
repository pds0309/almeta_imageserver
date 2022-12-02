package com.pds0309.almeta_imageserver.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "image")
public class ImageProperties {
    private final String realDirectory;
    private final String presentationDirectory;
}
