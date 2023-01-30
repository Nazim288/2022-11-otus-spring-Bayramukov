package ru.otus.home4.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LocalMessages {

    EXAM_RESULT("exam.result.message"),
    EXAM_SALUTATION("exam.salutation.message"),
    ASK_STUDENT_NAME("ask.student.name.message"),
    ASK_STUDENT_LASTNAME("ask.student.lastname.message"),
    ASK_QUESTION("ask.question.message"),
    REGISTER_ERROR("register.error.message"),
    NOT_HAVE_REGISTER_ERROR("not.have.register.error.message");
    private final String value;

}
