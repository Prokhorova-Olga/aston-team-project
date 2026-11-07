package ru.aston.project.threads;

import ru.aston.project.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultiThreadCounter {

    public int countOccurrences(List<Student> students, Student parameter) {
        if (students == null || students.isEmpty()) {
            return 0;
        }
        if (students.size() < 4) {
            return countPart(students, parameter);
        }
        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Future<Integer>> futures = startThreads(students, parameter, executor);

        int total = 0;
        for (Future<Integer> future : futures) {
            try {
                total += future.get();
            } catch (InterruptedException | ExecutionException e) {
                System.err.println("Ошибка при выполнении потока: " + e.getMessage());
                if (e instanceof InterruptedException) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        executor.shutdown();
        return total;
    }

    private int countPart(List<Student> subList, Student parameter) {
        int count = 0;
        for (Student student : subList) {
            if (student.equals(parameter)) {
                count++;
            }
        }
        return count;
    }

    private List<Future<Integer>> startThreads(List<Student> students, Student parameter, ExecutorService executorService) {
        List<Future<Integer>> futures = new ArrayList<>();
        int part = students.size() / 4;

        for (int i = 0; i < 4; i++) {
            int start = i * part;
            int end;
            if (i == 3) {
                end = students.size();
            } else {
                end = start + part;
            }
            List<Student> sublist = students.subList(start, end);
            Future<Integer> future = executorService.submit(() -> countPart(sublist, parameter));
            futures.add(future);
        }
        return futures;
    }
}
