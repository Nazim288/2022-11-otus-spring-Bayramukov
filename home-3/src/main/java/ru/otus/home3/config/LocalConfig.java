package ru.otus.home3.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.Locale;
@Getter
@Setter
@ConfigurationProperties(prefix = "application")
public class LocalConfig {
    private Locale locale;
}
