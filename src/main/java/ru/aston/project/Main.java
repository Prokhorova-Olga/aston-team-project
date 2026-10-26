
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Временная заглушка вместо List<Student>
        List<String> students = new ArrayList<>();

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
                            case "1" ->
                                    System.out.println("Тут будет чтение из файла.");
                            case "2" ->
                                    System.out.println("Тут будет случайное заполнение.");
                            case "3" ->
                                    System.out.println("Тут будет ручное заполнение.");
                            default ->
                                    System.out.println("Неизвестный способ заполнения.");
                        }

                        // Временная имитация результата заполнения
                        if (fillChoice.matches("[1-3]")) {
                            students.clear();

                            for (int i = 1; i <= count; i++) {
                                students.add("Тестовый студент №" + i);
                            }

                            System.out.println("Создано тестовых записей: " + students.size());
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
                        System.out.println("Тут будет сортировка: поле " + fieldChoice + ", алгоритм " + algorithmChoice);
                    }
                }

                case "4", "5", "6" ->
                        System.out.println("В разработке.");

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

    private static void printMenu() {
        System.out.println("===== МЕНЮ =====");
        System.out.println("1. Заполнить список студентов");
        System.out.println("2. Вывести список студентов");
        System.out.println("3. Отсортировать студентов");
        System.out.println("4. Дополнительная функция");
        System.out.println("5. Дополнительная функция");
        System.out.println("6. Дополнительная функция");
        System.out.println("0. Выход");
    }
}
