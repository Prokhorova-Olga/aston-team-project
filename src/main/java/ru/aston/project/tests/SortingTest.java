package ru.aston.project.tests;

import java.util.ArrayList;
import java.util.List;

public class SortingTest {
    public static void testSort() {

        /* Код для заглушек */

        Student first = new Student();
        first.averageGrade = 3.0f;

        Student second = new Student();
        second.averageGrade = 5.0f;

        Student third = new Student();
        third.averageGrade = 4.0f;

        List<Student> students = new ArrayList<>();
        students.add(first);
        students.add(second);
        students.add(third);

        SortStrategy strategy = new Alg1();
        strategy.sort(students);

        if (students.get(2).getAverageGrade() == 5) {
            System.out.println("Тест Сортировки ПРОЙДЕН.");
        } else System.out.println("Тест Сортировки ОШИБКА.");


        /* Код для реальных данных */

//        Student first = new StudentBuilder()
//                .setAverageGrade(3.0f)
//                .build();

//        Student second = new StudentBuilder()
//                .setAverageGrade(5.0f)
//                .build();

//        Student third = new StudentBuilder()
//                .setAverageGrade(4.0f)
//                .build();

//        List<Student> students = new ArrayList<>();
//        students.add(first);
//        students.add(second);
//        students.add(third);

//        SortStrategy strategy = new Alg1();
//        strategy.sort(students);
//
//        if (students.get(0).getAverageGrade() == 3){
//            System.out.println("Тест Сортировки ПРОЙДЕН.");
//        } else System.out.println("Тест Сортировки ОШИБКА.");
    }
}
