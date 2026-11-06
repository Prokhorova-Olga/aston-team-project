package ru.aston.project.collection;

import ru.aston.project.model.Student;

import java.util.*;

public class CustomList implements List<Student> {

    private static final int DEFAULT_CAPACITY = 15;
    private Student[] elements;
    private int size;

    public CustomList() {
        this.elements = new Student[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Student get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ", размер: " + size);
        }
        return elements[index];
    }

    @Override
    public boolean add(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Нельзя добавить null");
        }
        if (size == elements.length) {
            Student[] newElements = new Student[elements.length * 2];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            this.elements = newElements;
        }
        elements[size] = student;
        size++;
        return true;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<Student> iterator() {
        return new Iterator<>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public Student next() {
                return elements[currentIndex++];
            }
        };
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] array) {
        if (array.length < size) {
            return (T[]) Arrays.copyOf(elements, size, array.getClass());
        }
        System.arraycopy(elements, 0, array, 0, size);
        if (array.length > size) {
            array[size] = null;
        }
        return array;
    }

    @Override
    public Student set(int index, Student element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (element == null) {
            throw new IllegalArgumentException("Нельзя установить null");
        }
        Student oldValue = elements[index];
        elements[index] = element;
        return oldValue;
    }

    @Override
    public void add(int index, Student element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (element == null) {
            throw new IllegalArgumentException("Нельзя добавить null");
        }
        if (size == elements.length) {
            Student[] newElements = new Student[elements.length * 2];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements;
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    @Override
    public Student remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Student removed = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return removed;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index >= 0) {
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Student> collection) {
        boolean modified = false;
        for (Student student : collection) {
            add(student);
            modified = true;
        }
        return modified;
    }

    @Override
    public void clear() {
        Arrays.fill(elements, null);
        size = 0;
    }

    @Override
    public List<Student> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }
        List<Student> sub = new ArrayList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            sub.add(elements[i]);
        }
        return sub;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends Student> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<Student> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<Student> listIterator(int index) {
        throw new UnsupportedOperationException();
    }
}
