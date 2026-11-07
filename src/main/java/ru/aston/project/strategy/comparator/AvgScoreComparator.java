package ru.aston.project.strategy.comparator;

import ru.aston.project.model.Student;

import java.util.Comparator;

public class AvgScoreComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return Double.compare(o1.getAverageGrade(), o2.getAverageGrade());
    }
}