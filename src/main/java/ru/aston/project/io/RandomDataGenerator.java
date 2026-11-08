package ru.aston.project.io;

import ru.aston.project.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomDataGenerator {

    public List<Student> generateRandom(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Количество студентов не может быть отрицательным");
        }
        List<Student> students = new ArrayList<>();
        String[] groups = {"A-1", "A-2", "B-1", "B-2"};
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            int groupIndex = random.nextInt(groups.length);
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
}
