package ru.aston.project.validator;

import ru.aston.project.model.Student;

public abstract class AbstractStudentValidator implements StudentValidator {

    private StudentValidator next;

    @Override
    public boolean validate(Student student) {
        if (next != null) {
            return next.validate(student);
        }
        return true;
    }

    @Override
    public void setNext(StudentValidator next) {
        this.next = next;
    }
}
