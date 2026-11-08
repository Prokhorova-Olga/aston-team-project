package ru.aston.project.app;

import ru.aston.project.collection.CustomList;
import ru.aston.project.strategy.InsertionSort;
import ru.aston.project.strategy.QuickSort;
import ru.aston.project.strategy.SortStrategy;
import ru.aston.project.strategy.enums.SortField;

import java.util.Scanner;

public class SortMenuService {

    public void showSortMenu(Scanner scanner, CustomList students) {
        if (students.isEmpty()) {
            System.out.println("Список студентов пуст.");
            return;
        }

        SortField field = askSortField(scanner);
        if (field == null) return;

        System.out.println("Выберите алгоритм:");
        System.out.println("1. Сортировка вставками");
        System.out.println("2. Быстрая сортировка");
        System.out.print("Ваш выбор: ");
        String algorithmChoice = scanner.nextLine().trim();

        SortStrategy strategy;
        if (algorithmChoice.equals("1")) strategy = new InsertionSort();
        else if (algorithmChoice.equals("2")) strategy = new QuickSort();
        else {
            System.out.println("Некорректный выбор.");
            return;
        }

        strategy.sort(students, field.getComparator());
        System.out.println("Список отсортирован:");
        students.forEach(System.out::println);
    }

    private SortField askSortField(Scanner scanner) {
        System.out.println("Выберите поле сортировки:");
        System.out.println("1. Средний балл");
        System.out.println("2. Группа");
        System.out.println("3. Номер зачётки");
        System.out.print("Ваш выбор: ");
        String fieldChoice = scanner.nextLine().trim();

        if (!fieldChoice.matches("[1-3]")) {
            System.out.println("Некорректный выбор.");
            return null;
        }
        return SortField.fromCode(Integer.parseInt(fieldChoice));
    }
}


