package ru.aston.project.validator;

import ru.aston.project.model.Student;

public interface StudentValidator {

    void setNext(StudentValidator next);

    boolean validate(Student student);
}
