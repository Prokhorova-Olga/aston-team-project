package ru.aston.project.validator;

import org.junit.jupiter.api.Test;
import ru.aston.project.model.Student;

import static org.junit.jupiter.api.Assertions.*;

public class StudentValidatorServiceTest {
    @Test
    void testPositive() {
        Student student = Student.builder()
                .group("A-1")
                .averageGrade(4.5)
                .recordBook("12345")
                .build();

        StudentValidatorService studentValidatorService = new StudentValidatorService();
        assertTrue(studentValidatorService.validate(student));
    }

    @Test
    void testIncorrectGroup() {
        Student student = Student.builder()
                .group("")
                .averageGrade(4.5)
                .recordBook("12345")
                .build();

        StudentValidatorService studentValidatorService = new StudentValidatorService();
        assertFalse(studentValidatorService.validate(student));
    }

    @Test
    void testNegativeAverageGrade() {
        Student student = Student.builder()
                .group("A-1")
                .averageGrade(-1)
                .recordBook("12345")
                .build();

        StudentValidatorService studentValidatorService = new StudentValidatorService();
        assertFalse(studentValidatorService.validate(student));
    }

    @Test
    void testBigAverageGrade() {
        Student student = Student.builder()
                .group("A-1")
                .averageGrade(6)
                .recordBook("12345")
                .build();

        StudentValidatorService studentValidatorService = new StudentValidatorService();
        assertFalse(studentValidatorService.validate(student));
    }

    @Test
    void testIncorrectRecordBook() {
        Student student = Student.builder()
                .group("A-1")
                .averageGrade(4.5)
                .recordBook("")
                .build();

        StudentValidatorService studentValidatorService = new StudentValidatorService();
        assertFalse(studentValidatorService.validate(student));
    }

    @Test
    void validate_shouldReturnTrueForBoundaryAverageGrades() {
        StudentValidatorService service = new StudentValidatorService();

        Student minGradeStudent = Student.builder()
                .group("A-101")
                .averageGrade(0.0)
                .recordBook("111111")
                .build();
        Student maxGradeStudent = Student.builder()
                .group("B-202")
                .averageGrade(5.0)
                .recordBook("222222")
                .build();

        assertTrue(service.validate(minGradeStudent));
        assertTrue(service.validate(maxGradeStudent));
    }

    @Test
    void validate_shouldReturnFalseWhenGroupContainsOnlySpaces() {
        StudentValidatorService service = new StudentValidatorService();

        Student student = Student.builder()
                .group("   ")
                .averageGrade(4.5)
                .recordBook("111111")
                .build();

        assertFalse(service.validate(student));
    }

    @Test
    void validate_shouldReturnFalseForNullStudent() {
        StudentValidatorService service = new StudentValidatorService();

        assertDoesNotThrow(() -> assertFalse(service.validate(null)));
    }
}
