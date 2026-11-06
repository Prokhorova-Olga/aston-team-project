package ru.aston.project.strategy;

import ru.aston.project.model.Student;
import java.util.Comparator;
import java.util.List;

public interface SortStrategy {
    void sort(List<Student> students, Comparator<Student> comparator);
}