package ru.aston.project.threads;

import org.junit.jupiter.api.Test;
import ru.aston.project.model.Student;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiThreadCounterTest {

    private final MultiThreadCounter counter = new MultiThreadCounter();

    @Test
    void countOccurrences_shouldReturn500_whenEverySecondStudentMatches() {
        Student target = Student.builder()
                .group("A-1")
                .averageGrade(4.5)
                .recordBook("123")
                .build();

        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            if (i % 2 == 0) {
                students.add(target);
            } else {
                students.add(Student.builder()
                        .group("B-" + i)
                        .averageGrade(3.0)
                        .recordBook(String.valueOf(i))
                        .build());
            }
        }

        int result = counter.countOccurrences(students, target);

        assertEquals(500, result);
    }

    @Test
    void countOccurrences_shouldReturnZero_whenListIsEmpty() {
        Student target = Student.builder()
                .group("A-1")
                .averageGrade(4.5)
                .recordBook("123")
                .build();

        int result = counter.countOccurrences(new ArrayList<>(), target);

        assertEquals(0, result);
    }

    @Test
    void countOccurrences_shouldReturnZero_whenListIsNull() {
        Student target = Student.builder()
                .group("A-1")
                .averageGrade(4.5)
                .recordBook("123")
                .build();

        int result = counter.countOccurrences(null, target);

        assertEquals(0, result);
    }
}