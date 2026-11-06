package ru.aston.project.io;

import ru.aston.project.model.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DataWriter {

    private final String path;

    public DataWriter(String path) {
        this.path = path;
    }

    public void appendToFile(List<Student> students) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.path, true))) {
            for (Student student : students) {
                String line = student.getGroup() + "," + student.getAverageGrade() + "," + student.getRecordBook();
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }
}
