package ru.aston.project.app;

import ru.aston.project.collection.CustomList;
import ru.aston.project.io.DataReader;
import ru.aston.project.io.DataWriter;
import ru.aston.project.model.Student;
import ru.aston.project.threads.MultiThreadCounter;
import ru.aston.project.validator.StudentValidatorService;

import java.util.Scanner;

public class ConsoleApplication {

    public void start() {
        Scanner scanner = new Scanner(System.in);

        CustomList students = new CustomList();
        StudentValidatorService validator = new StudentValidatorService();
        DataReader reader = new DataReader(validator);
        MultiThreadCounter counter = new MultiThreadCounter();

        while (true) {
            printMenu();

            System.out.print("Выберите действие: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    System.out.println("Выберите способ заполнения:");
                    System.out.println("1. Из файла");
                    System.out.println("2. Случайным образом");
                    System.out.println("3. Вручную");

                    System.out.print("Ваш выбор: ");
                    String fillChoice = scanner.nextLine().trim();

                    System.out.print("Количество студентов: ");
                    String countInput = scanner.nextLine().trim();

                    try {
                        int count = Integer.parseInt(countInput);

                        if (count < 1) {
                            System.out.println("Количество должно быть больше нуля.");
                            break;
                        }

                        switch (fillChoice) {
                            case "1" -> {
                                students.clear();
                                students.addAll(reader.readFromFile("students.txt"));
                            }
                            case "2" -> {
                                students.clear();
                                students.addAll(reader.generateRandom(count));
                                System.out.println("Создано студентов: " + students.size());
                            }
                            case "3" -> {
                                students.clear();
                                students.addAll(reader.readFromConsole(count));
                                System.out.println("Добавлено студентов: " + students.size());
                            }
                            default ->
                                    System.out.println("Неизвестный способ заполнения.");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Введите количество целым числом.");
                    }
                }

                case "2" -> {
                    if (students.isEmpty()) {
                        System.out.println("Список студентов пуст.");
                    }
                    else {
                        students.forEach(System.out::println);
                    }
                }

                case "3" -> {
                    System.out.println("Выберите поле сортировки:");
                    System.out.println("1. Группа");
                    System.out.println("2. Средний балл");
                    System.out.println("3. Номер зачётки");

                    System.out.print("Ваш выбор: ");
                    String fieldChoice = scanner.nextLine().trim();

                    System.out.println("Выберите алгоритм:");
                    System.out.println("1. Алгоритм 1");
                    System.out.println("2. Алгоритм 2");

                    System.out.print("Ваш выбор: ");
                    String algorithmChoice = scanner.nextLine().trim();

                    if (!fieldChoice.matches("[1-3]") || !algorithmChoice.matches("[1-2]")) {
                        System.out.println("Некорректный выбор.");
                    }
                    else {
                        // сортировки Насти
                        System.out.println("Тут будет сортировка: поле " + fieldChoice + ", алгоритм " + algorithmChoice);
                    }
                }

                case "4" -> extrasMenu(scanner, students, counter);

                case "0" -> {
                    System.out.println("Выход из программы.");
                    scanner.close();
                    return;
                }

                default ->
                        System.out.println("Неизвестная команда. Выберите пункт меню.");
            }

            System.out.println();
        }
    }


    private void extrasMenu(Scanner scanner, CustomList students, MultiThreadCounter counter) {
        while (true) {
            printExtrasMenu();

            System.out.print("Выберите действие: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" ->
                        System.out.println("В разработке.");

                case "2" -> {
                    if (students.isEmpty()) {
                        System.out.println("Список студентов пуст.");
                    }
                    else {
                        System.out.print("Путь к файлу для записи: ");
                        String path = scanner.nextLine().trim();
                        new DataWriter(path).appendToFile(students);
                        System.out.println("Запись в файл выполнена.");
                    }
                }

                case "3" -> {
                    if (students.isEmpty()) {
                        System.out.println("Список студентов пуст.");
                    }
                    else {
                        System.out.println("Введите данные студента для подсчёта:");

                        System.out.print("Группа: ");
                        String group = scanner.nextLine().trim();

                        System.out.print("Средний балл: ");
                        double averageGrade;
                        try {
                            averageGrade = Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));
                        } catch (NumberFormatException e) {
                            System.out.println("Введите средний балл числом.");
                            break;
                        }

                        System.out.print("Номер зачётки: ");
                        String recordBook = scanner.nextLine().trim();

                        Student target = Student.builder()
                                .group(group)
                                .averageGrade(averageGrade)
                                .recordBook(recordBook)
                                .build();

                        int result = counter.countOccurrences(students, target);
                        System.out.println("Найдено совпадений: " + result);
                    }
                }

                case "0" -> {
                    return;
                }

                default ->
                        System.out.println("Неизвестная команда. Выберите пункт меню.");
            }

            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("===== МЕНЮ =====");
        System.out.println("1. Заполнить список студентов");
        System.out.println("2. Вывести список студентов");
        System.out.println("3. Отсортировать студентов");
        System.out.println("4. Дополнительные функции");
        System.out.println("0. Выход");
    }

    private static void printExtrasMenu() {
        System.out.println("===== ДОПОЛНИТЕЛЬНЫЕ ФУНКЦИИ =====");
        System.out.println("1. Кастомная сортировка");
        System.out.println("2. Запись в файл");
        System.out.println("3. Многопоточный подсчёт");
        System.out.println("0. Назад");
    }
}