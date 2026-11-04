package ru.aston.project.io;

import ru.aston.project.model.Student;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class DataReader {

    public List<Student> readFromFile(String path) {
        List<Student> students = new ArrayList<>();


        try {
            List<String> lines = Files.readAllLines(Path.of(path));
            int lineNumber = 0;

            for (String line : lines) {
                lineNumber++;

                String[] parts = line.split(",");
                if (parts.length != 3) {
                    System.out.println("Строка " + lineNumber
                            + ": неверная строка - " + line);
                    continue;
                }
                String group = parts[0].trim();
                if (group.isEmpty()) {
                    System.out.println("Строка " + lineNumber
                            + ": пустая группа - " + line);
                    continue;
                }
                String gradeText = parts[1].trim();
                String recordBook = parts[2].trim();
                if (recordBook.isEmpty()) {
                    System.out.println("Строка " + lineNumber
                            + ": пустой номер зачётной книжки - " + line);
                    continue;
                }
                double grade;
                try {
                    grade = Double.parseDouble(gradeText);
                } catch (NumberFormatException e) {
                    System.out.println("Строка " + lineNumber
                            + ": неверный формат среднего балла - " + gradeText);
                    continue;
                }
                if (!Double.isFinite(grade) || grade < 0 || grade > 5) {
                    System.out.println("Строка " + lineNumber
                            + ": недопустимый диапазон среднего балла - " + gradeText);
                    continue;
                }
                Student student = Student.builder()
                        .group(group)
                        .averageGrade(grade)
                        .recordBook(recordBook)
                        .build();

                students.add(student);
            }
            System.out.println("Количество студентов: " + students.size());

        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }

        return students;
    }

    public List<Student> generateRandom(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Количество студентов не может быть отрицательным");
        }
        List<Student> students = new ArrayList<>();
        String[] groups = {"A-1", "A-2", "B-1", "B-2"};
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int groupIndex = random.nextInt(4);
            String group = groups[groupIndex];

            double averageGrade = (random.nextInt(31) + 20) / 10.0;

            int recordBookNumber = random.nextInt(90000) + 10000;
            String recordBook = String.valueOf(recordBookNumber);

            Student student = Student.builder()
                    .group(group)
                    .averageGrade(averageGrade)
                    .recordBook(recordBook)
                    .build();

            students.add(student);
        }
        return students;
    }

    public List<Student> readFromConsole(int count) {
        if (count < 0) {
            throw new IllegalArgumentException(
                    "Количество студентов не может быть отрицательным");
        }
        List<Student> students = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < count; i++) {
            System.out.println("Ввод данных студента №" + (i + 1));
            String group = "";

            while (group.isEmpty()) {
                System.out.print("Введите группу: ");
                group = scanner.nextLine().trim();

                if (group.isEmpty()) {
                    System.out.println("Ошибка: группа не может быть пустой.");
                }
            }
            double grade = 0;
            boolean valid = false;

            while (!valid) {
                System.out.print("Введите средний балл: ");
                String gradeText = scanner.nextLine().trim();
                try {
                    grade = Double.parseDouble(gradeText);
                    if (!Double.isFinite(grade) || grade < 0 || grade > 5) {
                        System.out.println("Ошибка: средний балл должен быть числом от 0 до 5.");
                    } else {
                        valid = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: введите число.");
                }
            }
            System.out.print("Введите номер зачётной книжки: ");
            String recordBook = scanner.nextLine().trim();
            while (recordBook.isEmpty()) {
                System.out.println("Ошибка: номер зачётной книжки не может быть пустым.");
                System.out.print("Введите номер зачётной книжки: ");
                recordBook = scanner.nextLine().trim();
            }

            Student student = Student.builder()
                    .group(group)
                    .averageGrade(grade)
                    .recordBook(recordBook)
                    .build();

            students.add(student);

        }
        return students;
    }
}
