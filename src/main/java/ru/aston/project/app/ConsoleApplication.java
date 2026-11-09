package ru.aston.project.app;

import ru.aston.project.collection.CustomList;
import ru.aston.project.io.*;
import ru.aston.project.threads.MultiThreadCounter;
import ru.aston.project.validator.StudentValidatorService;

import java.util.List;
import java.util.Scanner;

public class ConsoleApplication {

    private final ru.aston.project.app.SortMenuService sortHandler = new ru.aston.project.app.SortMenuService();
    private final ru.aston.project.app.ExtraMenuService extrasHandler = new ru.aston.project.app
            .ExtraMenuService();

    public void start() {
        Scanner scanner = new Scanner(System.in);

        StudentValidatorService validator = new StudentValidatorService();
        FileDataReader fileReader = new FileDataReader();
        StudentParser parser = new StudentParser(validator);
        RandomDataGenerator generator = new RandomDataGenerator();
        ConsoleDataReader consoleReader = new ConsoleDataReader(validator);
        DataReader reader = new DataReader(fileReader, parser, generator, consoleReader);

        CustomList students = new CustomList();
        MultiThreadCounter counter = new MultiThreadCounter();

        while (true) {
            printMenu();
            System.out.print("Выберите действие: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> fillList(scanner, students, reader);
                case "2" -> {
                    if (students.isEmpty()) System.out.println("Список пуст.");
                    else students.forEach(System.out::println);
                }
                case "3" -> sortHandler.showSortMenu(scanner, students);
                case "4" -> extrasHandler.showExtrasMenu(scanner, students, counter);
                case "5" -> {
                    if (students.isEmpty()) System.out.println("Список уже пуст.");
                    else {
                        students.clear();
                        System.out.println("Список студентов успешно очищен.");
                    }
                }
                case "0" -> {
                    System.out.println("Выход.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Неизвестная команда.");
            }
            System.out.println();
        }
    }

    private void fillList(Scanner scanner, CustomList students, DataReader reader) {
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
                return;
            }

            switch (fillChoice) {
                case "1" -> {
                    int beforeSize = students.size();
                    List<ru.aston.project.model.Student> temp = reader.readFromFile("students.txt");
                    if (temp.size() > count) temp = temp.subList(0, count);
                    students.addAll(temp);
                    System.out.println("Загружено из файла: " + (students.size() - beforeSize) + ". Всего: " + students.size());
                }
                case "2" -> {
                    int beforeSize = students.size();
                    students.addAll(reader.generateRandom(count));
                    System.out.println("Сгенерировано: " + (students.size() - beforeSize) + ". Всего: " + students.size());
                }
                case "3" -> {
                    int beforeSize = students.size();
                    students.addAll(reader.readFromConsole(count));
                    System.out.println("Добавлено вручную: " + (students.size() - beforeSize) + ". Всего: " + students.size());
                }
                default -> System.out.println("Неизвестный способ заполнения.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Введите количество целым числом.");
        }
    }

    private static void printMenu() {
        System.out.println("===== МЕНЮ =====");
        System.out.println("1. Заполнить список студентов");
        System.out.println("2. Вывести список студентов");
        System.out.println("3. Отсортировать студентов");
        System.out.println("4. Дополнительные функции");
        System.out.println("5. Очистить список студентов");
        System.out.println("0. Выход");
    }
}

