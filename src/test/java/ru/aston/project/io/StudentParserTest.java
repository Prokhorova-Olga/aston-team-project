package ru.aston.project.io;

import org.junit.jupiter.api.Test;
import ru.aston.project.model.Student;
import ru.aston.project.validator.StudentValidatorService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentParserTest {

    private final StudentParser studentParser = new StudentParser(new StudentValidatorService());

    @Test
    void parseLines_shouldReturnStudents_whenLinesAreValid() {
        List<String> lines = List.of("A-1, 4.5, 12345", "B-2, 3.0, 67890");
        List<Student> students = studentParser.parseLines(lines);

        assertEquals(2, students.size());
        assertEquals("A-1", students.get(0).getGroup());
        assertEquals(4.5, students.get(0).getAverageGrade());
        assertEquals("12345", students.get(0).getRecordBook());
    }

    @Test
    void parseLines_shouldSkipInvalidLines() {
        List<String> lines = List.of(
                "A-1, 4.5, 12345",
                "A-1, 4.5",
                "A-1, ОШИБКА, 12345",
                "A-1, 10.0, 12345",
                "A-1, 4.5, 123"
        );
        List<Student> students = studentParser.parseLines(lines);
        assertEquals(1, students.size());
        assertEquals("A-1", students.get(0).getGroup());
        assertEquals(4.5, students.get(0).getAverageGrade());
        assertEquals("12345", students.get(0).getRecordBook());
    }

    @Test
    void parseLines_shouldReturnEmptyList_whenLinesIsEmpty() {
        List<Student> students = studentParser.parseLines(new ArrayList<>());
        assertNotNull(students, "Список не должен быть null");
        assertTrue(students.isEmpty());
    }
}
