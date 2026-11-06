package ru.aston.project.collection;

import org.junit.jupiter.api.Test;
import ru.aston.project.model.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomListTest {

    @Test
    void add_shouldExpandArray_whenCapacityExceeded() {
        CustomList list = new CustomList();
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
        CustomList list = new CustomList();

        assertThrows(IndexOutOfBoundsException.class, () -> list.get(25));
    }

    @Test
    void add_shouldThrowIllegalArgumentException_whenStudentIsNull() {
        CustomList list = new CustomList();

        assertThrows(IllegalArgumentException.class, () -> list.add((Student) null));
    }
}