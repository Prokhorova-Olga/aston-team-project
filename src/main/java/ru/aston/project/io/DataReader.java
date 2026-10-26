package ru.aston.project.io;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

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
                if (group.isEmpty()){
                    System.out.println("Строка " + lineNumber
                            + ": пустая группа - " + line);
                    continue;
                }
                String gradeText = parts[1].trim();
                String recordBook = parts[2].trim();
                if (recordBook.isEmpty()){
                    System.out.println("Строка " + lineNumber
                            + ": пустой номер зачётной книжки - " + line);
                    continue;
                }
                double grade;
                try {
                    grade = Double.parseDouble(gradeText);
                }catch (NumberFormatException e){
                    System.out.println("Строка " + lineNumber
                            + ": неверный формат среднего балла - " + gradeText);
                    continue;
                }
                if (!Double.isFinite(grade) || grade < 0 || grade > 5) {
                    System.out.println("Строка " + lineNumber
                            + ": недопустимый диапазон среднего балла - " + gradeText);
                    continue;
                }
                Student student = new Student();
                student.setGroup(group);
                student.setAverageGrade(grade);
                student.setRecordBook(recordBook);
                students.add(student);
            }
            System.out.println("Количество студентов: " + students.size());

        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }

        return students;
    }
}
