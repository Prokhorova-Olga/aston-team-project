package ru.aston.project.io;

import ru.aston.project.model.Student;
import ru.aston.project.validator.StudentValidatorService;

import java.util.ArrayList;
import java.util.List;

public class StudentParser {

    private final StudentValidatorService validatorService;

    public StudentParser(StudentValidatorService validatorService) {
        this.validatorService = validatorService;
    }

    public List<Student> parseLines(List<String> lines) {
        List<Student> students = new ArrayList<>();
        int lineNumber = 0;

        for (String line : lines) {
            lineNumber++;

            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split(",");
            if (parts.length != 3) {
                System.out.println("Строка " + lineNumber
                        + ": неверный формат - " + line);
                continue;
            }

            String group = parts[0].trim();
            String gradeText = parts[1].trim();
            String recordBook = parts[2].trim();
            double grade;

            if (!group.matches("^[A-ZА-Я]-\\d+$")) {
                System.out.println("Строка " + lineNumber + ": неверный формат группы - " + group);
                continue;
            }
            if (!recordBook.matches("^\\d{5}$")) {
                System.out.println("Строка " + lineNumber + ": неверный формат зачетки - " + recordBook);
                continue;
            }

            try {
                grade = Double.parseDouble(gradeText);
            } catch (NumberFormatException e) {
                System.out.println("Строка " + lineNumber
                        + ": неверный формат среднего балла - " + gradeText);
                continue;
            }
            Student student = Student.builder()
                    .group(group)
                    .averageGrade(grade)
                    .recordBook(recordBook)
                    .build();

            if (validatorService.validate(student)) {
                students.add(student);
            }
        }
        return students;
    }
}
