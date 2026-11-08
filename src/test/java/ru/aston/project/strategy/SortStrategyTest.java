package ru.aston.project.strategy;

import org.junit.jupiter.api.Test;
import ru.aston.project.model.Student;
import ru.aston.project.strategy.comparator.AvgScoreComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortStrategyTest {
    @Test
    void sort_shouldSortByAverageGradeForInsertionSortAndQuickSort() {
        Comparator<Student> comparator = new AvgScoreComparator();

        List<Student> insertionList = new ArrayList<>(List.of(
                Student.builder().group("A-101").averageGrade(5.0).recordBook("111111").build(),
                Student.builder().group("B-202").averageGrade(3.0).recordBook("222222").build(),
                Student.builder().group("C-303").averageGrade(4.0).recordBook("333333").build()
        ));
        List<Student> quickList = new ArrayList<>(List.of(
                Student.builder().group("A-101").averageGrade(5.0).recordBook("111111").build(),
                Student.builder().group("B-202").averageGrade(3.0).recordBook("222222").build(),
                Student.builder().group("C-303").averageGrade(4.0).recordBook("333333").build()
        ));

        new InsertionSort().sort(insertionList, comparator);
        new QuickSort().sort(quickList, comparator);

        assertEquals(3.0, insertionList.get(0).getAverageGrade());
        assertEquals(4.0, insertionList.get(1).getAverageGrade());
        assertEquals(5.0, insertionList.get(2).getAverageGrade());

        assertEquals(3.0, quickList.get(0).getAverageGrade());
        assertEquals(4.0, quickList.get(1).getAverageGrade());
        assertEquals(5.0, quickList.get(2).getAverageGrade());
    }

    @Test
    void sort_shouldNotThrowForNullAndEmptyList() {
        Comparator<Student> comparator = new AvgScoreComparator();

        assertDoesNotThrow(() -> new InsertionSort().sort(null, comparator));
        assertDoesNotThrow(() -> new InsertionSort().sort(new ArrayList<>(), comparator));

        assertDoesNotThrow(() -> new QuickSort().sort(null, comparator));
        assertDoesNotThrow(() -> new QuickSort().sort(new ArrayList<>(), comparator));
    }
}
