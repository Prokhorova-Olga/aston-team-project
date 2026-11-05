package ru.aston.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
    @Test
    void testBuilderCreatesStudentCorrectly() {
        Student student = Student.builder()
                .group("A-1")
                .averageGrade(4.5)
                .recordBook("12345")
                .build();

        assertEquals("A-1", student.getGroup());
        assertEquals(4.5, student.getAverageGrade());
        assertEquals("12345", student.getRecordBook());
    }

    @Test
    void testEqualsTrue() {
        Student student1 = Student.builder()
                .group("A-1")
                .averageGrade(4.5)
                .recordBook("12345")
                .build();

        Student student2 = Student.builder()
                .group("A-1")
                .averageGrade(4.5)
                .recordBook("12345")
                .build();

        assertTrue(student1.equals(student2));
    }

    @Test
    void testEqualsFalse() {
        Student student1 = Student.builder()
                .group("A-1")
                .averageGrade(4.5)
                .recordBook("12345")
                .build();

        Student student2 = Student.builder()
                .group("A-2")
                .averageGrade(4.6)
                .recordBook("12346")
                .build();

        assertFalse(student1.equals(student2));
    }

}
