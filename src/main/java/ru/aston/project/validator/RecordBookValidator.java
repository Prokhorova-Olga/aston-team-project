package ru.aston.project.validator;

import ru.aston.project.model.Student;

public class RecordBookValidator extends AbstractStudentValidator {

    @Override
    public boolean validate(Student student) {
        if (student.getRecordBook() == null || student.getRecordBook().trim().isEmpty()) {
            System.out.println("Недопустимое значение: у зачетной книжки должен быть номер");
            return false;
        }
        return super.validate(student);
    }
}
