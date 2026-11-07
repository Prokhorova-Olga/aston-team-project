package ru.aston.project.strategy.comparator;

import ru.aston.project.model.Student;

import java.util.Comparator;

public class GroupNumComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return Comparator.nullsFirst(String::compareTo).compare(o1.getGroup(), o2.getGroup());
    }
}
