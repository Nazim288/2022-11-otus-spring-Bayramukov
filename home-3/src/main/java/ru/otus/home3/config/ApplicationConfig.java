package ru.otus.home3.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(LocalConfig.class)
public class ApplicationConfig {
}
