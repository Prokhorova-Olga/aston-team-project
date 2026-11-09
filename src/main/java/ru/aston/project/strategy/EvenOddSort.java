package ru.aston.project.strategy;

import ru.aston.project.model.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EvenOddSort implements SortStrategy {

    @Override
    public void sort(List<Student> studentList, Comparator<Student> comparator) {
        if (studentList == null || studentList.isEmpty()) {
            return;
        }
        List<Student> evenStudents = getEvenStudents(studentList);
        sortEvenStudents(evenStudents, comparator);
        mergeStudentBack(studentList, evenStudents);
    }

    private List<Student> getEvenStudents(List<Student> studentList) {
        List<Student> result = new ArrayList<>();
        if (studentList == null) {
            return result;
        }
        for (Student student : studentList) {
            if ((int) student.getAverageGrade() % 2 == 0) {
                result.add(student);
            }
        }
        return result;
    }

    private void sortEvenStudents(List<Student> evenStudents, Comparator<Student> comparator) {
        InsertionSort sorter = new InsertionSort();
        sorter.sort(evenStudents, comparator);
    }

    private void mergeStudentBack(List<Student> original, List<Student> sortedEven) {
        int evenIndex = 0;
        for (int i = 0; i < original.size(); i++) {
            Student currentStudent = original.get(i);
            if ((int) currentStudent.getAverageGrade() % 2 == 0) {
                original.set(i, sortedEven.get(evenIndex));
                evenIndex++;
            }
        }
    }
}
