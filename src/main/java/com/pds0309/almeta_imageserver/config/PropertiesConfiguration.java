package com.pds0309.almeta_imageserver.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = {ImageProperties.class})
public class PropertiesConfiguration {

}