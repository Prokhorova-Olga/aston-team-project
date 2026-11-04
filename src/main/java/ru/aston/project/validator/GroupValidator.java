package ru.aston.project.validator;

import ru.aston.project.model.Student;

public class GroupValidator extends AbstractStudentValidator {

    @Override
    public boolean validate(Student student) {
        if (student.getGroup() == null || student.getGroup().trim().isEmpty()) {
            System.out.println("Недопустимое значение: Группа не может быть пустой");
            return false;
        }
        return super.validate(student);
    }
}
