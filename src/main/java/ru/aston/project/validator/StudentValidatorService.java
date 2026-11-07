package ru.aston.project.validator;

import ru.aston.project.model.Student;

public class StudentValidatorService {

    private final StudentValidator firstValidator;

    public StudentValidatorService() {

        StudentValidator groupValidator = new GroupValidator();
        StudentValidator averageGradeValidator = new AverageGradeValidator();
        StudentValidator recordBookValidator = new RecordBookValidator();

        groupValidator.setNext(recordBookValidator);
        recordBookValidator.setNext(averageGradeValidator);

        this.firstValidator = groupValidator;
    }

    public boolean validate(Student student) {
        return firstValidator.validate(student);
    }
}
