package ru.aston.project.io;

import ru.aston.project.model.Student;
import ru.aston.project.validator.StudentValidatorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleDataReader {

    private final StudentValidatorService validatorService;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleDataReader(StudentValidatorService validatorService) {
        this.validatorService = validatorService;
    }

    public List<Student> readFromConsole(int count) {
        if (count < 0) {
            throw new IllegalArgumentException(
                    "Количество студентов не может быть отрицательным");
        }

        List<Student> students = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            boolean valid = false;

            while (!valid) {
                System.out.println("Ввод данных студента №" + (i + 1));
                System.out.print("Введите группу(формат A-1): ");
                String group = scanner.nextLine().trim();
                if (!group.matches("^[A-ZА-Я]-\\d+$")) {
                    System.out.println("Ошибка: группа должна быть в формате 'Буква-Цифра' (например, A-1).");
                    continue;
                }
                double grade;

                while (true) {
                    System.out.print("Введите средний балл:(от 0 до 5) ");
                    String gradeText = scanner.nextLine().trim();

                    try {
                        grade = Double.parseDouble(gradeText);
                        if (grade < 0 || grade > 5) {
                            System.out.println("Ошибка: средний балл должен быть от 0 до 5.");
                            continue;
                        }
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка: введите число.");
                    }
                }
                while (true) {
                    System.out.print("Введите номер зачётной книжки(5 цифр): ");
                    String recordBook = scanner.nextLine().trim();
                    if (!recordBook.matches("^\\d{5}$")) {
                        System.out.println("Ошибка: номер зачетки должен состоять ровно из 5 цифр.");
                        continue;
                    }
                    Student student = Student.builder()
                            .group(group)
                            .averageGrade(grade)
                            .recordBook(recordBook)
                            .build();

                    if (validatorService.validate(student)) {
                        students.add(student);
                        valid = true;
                        break;
                    } else {
                        System.out.println("Студент не прошел валидацию. Попробуйте снова.");
                    }
                }
            }
        }
        return students;
    }
}