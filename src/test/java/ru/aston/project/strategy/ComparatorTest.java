package ru.aston.project.strategy;

import org.junit.jupiter.api.Test;
import ru.aston.project.model.Student;
import ru.aston.project.strategy.comparator.AvgScoreComparator;
import ru.aston.project.strategy.comparator.GroupNumComparator;
import ru.aston.project.strategy.comparator.StudentIdComparator;

import static org.junit.jupiter.api.Assertions.*;

public class ComparatorTest {
    @Test
    void compare_shouldReturnExpectedResultsForAvgScoreGroupNumAndStudentId() {
        Student lowGrade = Student.builder()
                .group("A-101")
                .averageGrade(3.0)
                .recordBook("111111")
                .build();
        Student highGrade = Student.builder()
                .group("B-202")
                .averageGrade(5.0)
                .recordBook("222222")
                .build();

        Student sameGroupAndBook1 = Student.builder()
                .group("A-101")
                .averageGrade(4.0)
                .recordBook("111111")
                .build();
        Student sameGroupAndBook2 = Student.builder()
                .group("A-101")
                .averageGrade(2.0)
                .recordBook("111111")
                .build();

        assertTrue(new AvgScoreComparator().compare(lowGrade, highGrade) < 0);

        assertEquals(0, new GroupNumComparator().compare(sameGroupAndBook1, sameGroupAndBook2));
        assertEquals(0, new StudentIdComparator().compare(sameGroupAndBook1, sameGroupAndBook2));
    }

    @Test
    void compare_shouldNotThrowNullPointerExceptionForNullGroupAndRecordBook() {
        Student nullGroup = Student.builder()
                .group(null)
                .averageGrade(4.0)
                .recordBook("111111")
                .build();
        Student anotherNullGroup = Student.builder()
                .group(null)
                .averageGrade(3.0)
                .recordBook("222222")
                .build();
        Student withGroup = Student.builder()
                .group("A-101")
                .averageGrade(5.0)
                .recordBook("333333")
                .build();

        Student nullRecordBook = Student.builder()
                .group("A-101")
                .averageGrade(4.0)
                .recordBook(null)
                .build();
        Student anotherNullRecordBook = Student.builder()
                .group("B-202")
                .averageGrade(3.0)
                .recordBook(null)
                .build();
        Student withRecordBook = Student.builder()
                .group("C-303")
                .averageGrade(5.0)
                .recordBook("111111")
                .build();

        GroupNumComparator groupComparator = new GroupNumComparator();
        StudentIdComparator idComparator = new StudentIdComparator();

        assertDoesNotThrow(() -> groupComparator.compare(nullGroup, anotherNullGroup));
        assertDoesNotThrow(() -> groupComparator.compare(nullGroup, withGroup));
        assertDoesNotThrow(() -> groupComparator.compare(withGroup, nullGroup));

        assertDoesNotThrow(() -> idComparator.compare(nullRecordBook, anotherNullRecordBook));
        assertDoesNotThrow(() -> idComparator.compare(nullRecordBook, withRecordBook));
        assertDoesNotThrow(() -> idComparator.compare(withRecordBook, nullRecordBook));

        assertEquals(0, groupComparator.compare(nullGroup, anotherNullGroup));
        assertTrue(groupComparator.compare(nullGroup, withGroup) < 0);
        assertTrue(groupComparator.compare(withGroup, nullGroup) > 0);

        assertEquals(0, idComparator.compare(nullRecordBook, anotherNullRecordBook));
        assertTrue(idComparator.compare(nullRecordBook, withRecordBook) < 0);
        assertTrue(idComparator.compare(withRecordBook, nullRecordBook) > 0);
    }
}
