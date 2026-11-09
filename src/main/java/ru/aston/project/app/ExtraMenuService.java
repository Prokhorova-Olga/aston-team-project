package ru.aston.project.app;

import ru.aston.project.collection.CustomList;
import ru.aston.project.io.DataWriter;
import ru.aston.project.model.Student;
import ru.aston.project.strategy.EvenOddSort;
import ru.aston.project.threads.MultiThreadCounter;

import java.util.Comparator;
import java.util.Scanner;


public class ExtraMenuService {

    public void showExtrasMenu(Scanner scanner, CustomList students, MultiThreadCounter counter) {
        while (true) {
            printExtrasMenu();
            System.out.print("Выберите действие: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    if (students.isEmpty()) System.out.println("Список студентов пуст.");
                    else {
                        new EvenOddSort().sort(students, Comparator.comparingDouble(Student::getAverageGrade));
                        System.out.println("Кастомная сортировка (по среднему баллу, по целым числам) выполнена:");
                        students.forEach(System.out::println);
                    }
                }
                case "2" -> writeToFile(scanner, students);
                case "3" -> searchMenu(scanner, students, counter);
                case "0" -> {
                    return;
                }
                default -> System.out.println("Неизвестная команда. Выберите пункт меню.");
            }
            System.out.println();
        }
    }

    private void writeToFile(Scanner scanner, CustomList students) {
        if (students.isEmpty()) {
            System.out.println("Список студентов пуст.");
            return;
        }
        System.out.print("Введите имя файла для записи (например, output.txt): ");
        String fileName = scanner.nextLine().trim();
        if (!fileName.endsWith(".txt")) {
            fileName += ".txt";
        }
        new DataWriter(fileName).appendToFile(students);
        System.out.println("Данные успешно записаны в файл: " + fileName);
    }

    private void searchMenu(Scanner scanner, CustomList students, MultiThreadCounter counter) {
        if (students.isEmpty()) {
            System.out.println("Список студентов пуст.");
            return;
        }
        System.out.println("Выберите режим поиска:");
        System.out.println("1. По всему объекту (полное совпадение)");
        System.out.println("2. По конкретному полю");
        System.out.print("Ваш выбор: ");
        String searchMode = scanner.nextLine().trim();

        if (searchMode.equals("1")) {
            searchByFullMatch(scanner, students, counter);
        } else if (searchMode.equals("2")) {
            searchByField(scanner, students);
        } else {
            System.out.println("Некорректный выбор.");
        }
    }

    private void searchByFullMatch(Scanner scanner, CustomList students, MultiThreadCounter counter) {
        String group;
        while (true) {
            System.out.print("Группа (формат A-1): ");
            group = scanner.nextLine().trim();
            if (group.isEmpty() || !group.matches("^[A-ZА-Я]-\\d+$")) {
                System.out.println("Ошибка: неверный формат группы. Попробуйте снова.");
                continue;
            }
            break;
        }

        double averageGrade;
        while (true) {
            System.out.print("Средний балл (от 0 до 5): ");
            try {
                averageGrade = Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число.");
                continue;
            }
            if (averageGrade < 0 || averageGrade > 5) {
                System.out.println("Ошибка: балл должен быть от 0 до 5.");
                continue;
            }
            break;
        }

        String recordBook;
        while (true) {
            System.out.print("Номер зачётки (5 цифр): ");
            recordBook = scanner.nextLine().trim();
            if (recordBook.isEmpty() || !recordBook.matches("^\\d{5}$")) {
                System.out.println("Ошибка: номер зачетки должен состоять ровно из 5 цифр.");
                continue;
            }
            break;
        }

        Student target = Student.builder()
                .group(group).averageGrade(averageGrade).recordBook(recordBook).build();

        int result = counter.countOccurrences(students, target);
        System.out.println("Найдено полных совпадений: " + result);
    }

    private void searchByField(Scanner scanner, CustomList students) {
        System.out.println("Выберите поле:");
        System.out.println("1. Группа");
        System.out.println("2. Средний балл");
        System.out.println("3. Номер зачётки");
        System.out.print("Ваш выбор: ");
        String fieldChoice = scanner.nextLine().trim();

        int result = 0;

        if (fieldChoice.equals("1")) {
            String group;
            while (true) {
                System.out.print("Введите группу (формат A-1): ");
                group = scanner.nextLine().trim();
                if (group.isEmpty() || !group.matches("^[A-ZА-Я]-\\d+$")) {
                    System.out.println("Ошибка: неверный формат группы.");
                    continue;
                }
                break;
            }
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getGroup().equals(group)) result++;
            }
            System.out.println("Найдено с такой группой: " + result);

        } else if (fieldChoice.equals("2")) {
            double grade;
            while (true) {
                System.out.print("Введите балл (от 0 до 5): ");
                try {
                    grade = Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: введите число.");
                    continue;
                }
                if (grade < 0 || grade > 5) {
                    System.out.println("Ошибка: балл от 0 до 5.");
                    continue;
                }
                break;
            }
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getAverageGrade() == grade) result++;
            }
            System.out.println("Найдено с таким баллом: " + result);

        } else if (fieldChoice.equals("3")) {
            String recordBook;
            while (true) {
                System.out.print("Введите зачетку (5 цифр): ");
                recordBook = scanner.nextLine().trim();
                if (recordBook.isEmpty() || !recordBook.matches("^\\d{5}$")) {
                    System.out.println("Ошибка: зачетка должна быть из 5 цифр.");
                    continue;
                }
                break;
            }
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getRecordBook().equals(recordBook)) result++;
            }
            System.out.println("Найдено с такой зачеткой: " + result);
        } else {
            System.out.println("Некорректный выбор.");
        }
    }

    private static void printExtrasMenu() {
        System.out.println("===== ДОПОЛНИТЕЛЬНЫЕ ФУНКЦИИ =====");
        System.out.println("1. Кастомная сортировка по среднему баллу");
        System.out.println("2. Запись в файл");
        System.out.println("3. Многопоточный подсчёт");
        System.out.println("0. Назад");
    }
}
