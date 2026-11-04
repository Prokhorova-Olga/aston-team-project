package ru.aston.project.validator;

import ru.aston.project.model.Student;

public class AverageGradeValidator extends AbstractStudentValidator {

    @Override
    public boolean validate(Student student) {
        if (student.getAverageGrade() < 0 || student.getAverageGrade() > 5) {
            System.out.println("Недопустимое значение: Средний балл должен быть от 0 до 5");
            return false;
        }
        return super.validate(student);
    }
}
