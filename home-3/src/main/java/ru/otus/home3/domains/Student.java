package ru.otus.home3.domains;

import lombok.Data;

@Data
public class Student {
    private String id;
    private String name;
    private String lastName;
    private long points;
}
