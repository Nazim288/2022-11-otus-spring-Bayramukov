package ru.otus.home4.domains;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    private String name;
    private String lastName;
    private long points;

    public Student(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }
}
