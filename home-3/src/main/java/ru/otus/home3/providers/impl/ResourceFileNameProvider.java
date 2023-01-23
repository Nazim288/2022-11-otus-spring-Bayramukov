package ru.otus.home3.providers.impl;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Data
@Component
@ConfigurationProperties(prefix = "resource.files")
public class ResourceFileNameProvider {

    private  String questionsResource;
    private  String answersResource;
    private  String tasksResource;
    private  String studentResource;
    private  String examResource;

}
