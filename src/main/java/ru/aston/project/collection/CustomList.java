package ru.aston.project.collection;

import ru.aston.project.model.Student;

public class CustomList {
    private static final int DEFAULT_CAPACITY = 15;
    private Student[] elements;
    private int size;

    public CustomList() {
        this.elements = new Student[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Student get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ", размер: " + size);
        }
        return elements[index];
    }

    public void add(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Нельзя добавить null в список");
        }
        if (size == elements.length) {
            Student[] newElements = new Student[elements.length * 2];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            this.elements = newElements;
        }
        elements[size] = student;
        size++;
    }
}
