package ru.aston.project.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aston.project.model.Student;

import static org.junit.jupiter.api.Assertions.*;

public class CustomListTest {
    private CustomList list;

    @BeforeEach
    void setUp() {
        list = new CustomList();
    }

    @Test
    void add_shouldExpandArray_whenCapacityExceeded() {
        Student[] added = new Student[20];

        for (int i = 0; i < 20; i++) {
            added[i] = Student.builder()
                    .group("A-" + i)
                    .averageGrade(3.0 + (i % 3))
                    .recordBook(String.valueOf(i))
                    .build();
            list.add(added[i]);
        }

        assertEquals(20, list.size());
        assertEquals(added[15], list.get(15));
    }

    @Test
    void get_shouldThrowIndexOutOfBoundsException_whenIndexIsOutOfRange() {

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(25));
    }

    @Test
    void add_shouldThrowIllegalArgumentException_whenStudentIsNull() {

        assertThrows(IllegalArgumentException.class, () -> list.add((Student) null));
    }

    @Test
    void addInMiddle_shouldInsertElementAndShiftOthers() {
        Student s0 = Student.builder()
                .group("A-101")
                .averageGrade(4.5)
                .recordBook("111111")
                .build();
        Student s1 = Student.builder()
                .group("B-202")
                .averageGrade(3.8)
                .recordBook("222222")
                .build();
        Student s2 = Student.builder()
                .group("C-303")
                .averageGrade(4.9)
                .recordBook("333333")
                .build();
        Student inserted = Student.builder()
                .group("D-404")
                .averageGrade(4.1)
                .recordBook("444444")
                .build();

        list.add(s0);
        list.add(s1);
        list.add(s2);

        list.add(1, inserted);

        assertEquals(4, list.size());
        assertSame(s0, list.get(0));
        assertSame(inserted, list.get(1));
        assertSame(s1, list.get(2));
        assertSame(s2, list.get(3));
    }

    @Test
    void removeByIndex_shouldRemoveElementAndShiftLeft() {
        Student s0 = Student.builder()
                .group("A-101")
                .averageGrade(4.5)
                .recordBook("111111")
                .build();
        Student s1 = Student.builder()
                .group("B-202")
                .averageGrade(3.8)
                .recordBook("222222")
                .build();
        Student s2 = Student.builder()
                .group("C-303")
                .averageGrade(4.9)
                .recordBook("333333")
                .build();

        list.add(s0);
        list.add(s1);
        list.add(s2);

        Student removed = list.remove(1);

        assertSame(s1, removed);
        assertEquals(2, list.size());
        assertSame(s0, list.get(0));
        assertSame(s2, list.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(2));
    }

    @Test
    void contains_shouldReturnTrueForAddedAndFalseForMissingStudent() {
        Student added = Student.builder()
                .group("A-101")
                .averageGrade(4.5)
                .recordBook("111111")
                .build();
        Student missing = Student.builder()
                .group("B-202")
                .averageGrade(3.8)
                .recordBook("222222")
                .build();

        list.add(added);

        assertTrue(list.contains(added));
        assertFalse(list.contains(missing));
    }

    @Test
    void removeByIndex_shouldThrowWhenIndexOutOfBounds() {
        Student s0 = Student.builder()
                .group("A-101")
                .averageGrade(4.5)
                .recordBook("111111")
                .build();
        Student s1 = Student.builder()
                .group("B-202")
                .averageGrade(3.8)
                .recordBook("222222")
                .build();

        list.add(s0);
        list.add(s1);

        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(10));

        assertEquals(2, list.size());
        assertSame(s0, list.get(0));
        assertSame(s1, list.get(1));
    }

    @Test
    void clear_shouldRemoveAllElements() {
        Student s0 = Student.builder()
                .group("A-101")
                .averageGrade(4.5)
                .recordBook("111111")
                .build();
        Student s1 = Student.builder()
                .group("B-202")
                .averageGrade(3.8)
                .recordBook("222222")
                .build();

        list.add(s0);
        list.add(s1);

        list.clear();

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }
}