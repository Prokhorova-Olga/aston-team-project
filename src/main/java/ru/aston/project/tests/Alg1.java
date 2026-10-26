package ru.aston.project.tests;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Alg1 implements SortStrategy, Comparator<Student> {

    @Override
    public int compare(Student student1, Student student2) {
        return Float.compare(student1.averageGrade, student2.averageGrade);
    }

    /**
     * Сортирует список студентов по среднему баллу (по возрастанию)
     * сортировкой слиянием. Сортировка устойчивая, O(n log n).
     */
    @Override
    public void sort(List<Student> students) {
        if (students == null) {
            throw new NullPointerException("students не должен быть null");
        }
        if (students.size() < 2) {
            return;
        }

        List<Student> sorted = mergeSort(new ArrayList<>(students));

        for (int i = 0; i < sorted.size(); i++) {
            students.set(i, sorted.get(i));
        }
    }

    private List<Student> mergeSort(List<Student> list) {
        if (list.size() < 2) {
            return list;
        }

        int mid = list.size() / 2;
        List<Student> left = mergeSort(new ArrayList<>(list.subList(0, mid)));
        List<Student> right = mergeSort(new ArrayList<>(list.subList(mid, list.size())));

        return merge(left, right);
    }

    private List<Student> merge(List<Student> left, List<Student> right) {
        List<Student> result = new ArrayList<>(left.size() + right.size());
        int i = 0;
        int j = 0;

        while (i < left.size() && j < right.size()) {
            // <= 0 сохраняет порядок равных элементов (устойчивость)
            if (compare(left.get(i), right.get(j)) <= 0) {
                result.add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }

        while (i < left.size()) {
            result.add(left.get(i++));
        }
        while (j < right.size()) {
            result.add(right.get(j++));
        }

        return result;
    }
}
