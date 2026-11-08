package ru.aston.project.strategy;

import org.junit.jupiter.api.Test;
import ru.aston.project.model.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EvenOddSortTest {
    @Test
    void EvenOddSort() {
        Student s4 = Student.builder().group("A-1").averageGrade(4.0).recordBook("1").build();
        Student s3 = Student.builder().group("A-1").averageGrade(3.0).recordBook("2").build();
        Student s2 = Student.builder().group("A-1").averageGrade(2.0).recordBook("3").build();
        Student s5 = Student.builder().group("A-1").averageGrade(5.0).recordBook("4").build();

        List<Student> students = new ArrayList<>(List.of(s4, s3, s2, s5));
        new EvenOddSort().sort(students, Comparator.comparingDouble(Student::getAverageGrade));

        assertEquals(2.0, students.get(0).getAverageGrade());
        assertEquals(3.0, students.get(1).getAverageGrade());
        assertEquals(List.of(s2, s3, s4, s5), students);
    }

    @Test
    void sort_shouldSortEvenGradesAndKeepOddOnTheirPlaces() {
        Student s0 = Student.builder()
                .group("A-101")
                .averageGrade(4.0)
                .recordBook("111111")
                .build();
        Student s1 = Student.builder()
                .group("B-202")
                .averageGrade(3.0)
                .recordBook("222222")
                .build();
        Student s2 = Student.builder()
                .group("C-303")
                .averageGrade(2.0)
                .recordBook("333333")
                .build();
        Student s3 = Student.builder()
                .group("D-404")
                .averageGrade(5.0)
                .recordBook("444444")
                .build();

        List<Student> students = new ArrayList<>(List.of(s0, s1, s2, s3));

        new EvenOddSort().sort(students, Comparator.comparingDouble(Student::getAverageGrade));

        assertEquals(2.0, students.get(0).getAverageGrade());
        assertEquals(3.0, students.get(1).getAverageGrade());
        assertEquals(4.0, students.get(2).getAverageGrade());
        assertEquals(5.0, students.get(3).getAverageGrade());
    }

    @Test
    void sort_shouldSortListWithOnlyEvenGrades() {
        Student s0 = Student.builder()
                .group("A-101")
                .averageGrade(4.0)
                .recordBook("111111")
                .build();
        Student s1 = Student.builder()
                .group("B-202")
                .averageGrade(2.0)
                .recordBook("222222")
                .build();

        List<Student> students = new ArrayList<>(List.of(s0, s1));

        new EvenOddSort().sort(students, Comparator.comparingDouble(Student::getAverageGrade));

        assertEquals(2.0, students.get(0).getAverageGrade());
        assertEquals(4.0, students.get(1).getAverageGrade());
        assertSame(s1, students.get(0));
        assertSame(s0, students.get(1));
    }

    @Test
    void sort_shouldKeepListWithOnlyOddGradesUnchanged() {
        Student s0 = Student.builder()
                .group("A-101")
                .averageGrade(5.0)
                .recordBook("111111")
                .build();
        Student s1 = Student.builder()
                .group("B-202")
                .averageGrade(3.0)
                .recordBook("222222")
                .build();

        List<Student> students = new ArrayList<>(List.of(s0, s1));

        new EvenOddSort().sort(students, Comparator.comparingDouble(Student::getAverageGrade));

        assertEquals(5.0, students.get(0).getAverageGrade());
        assertEquals(3.0, students.get(1).getAverageGrade());
        assertSame(s0, students.get(0));
        assertSame(s1, students.get(1));
    }
}
