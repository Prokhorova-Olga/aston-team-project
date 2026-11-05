package ru.aston.project.tests;

import ru.aston.project.model.Student;

import java.util.List;

public interface SortStrategy {
    void sort(List<Student> students);
}

