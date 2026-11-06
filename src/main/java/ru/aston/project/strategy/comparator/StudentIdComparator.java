package ru.aston.project.strategy.comparator;

import ru.aston.project.model.Student;

import java.util.Comparator;

public class StudentIdComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getRecordBook().compareTo(o2.getRecordBook());
    }
}