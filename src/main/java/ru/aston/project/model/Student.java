package ru.aston.project.model;

import java.util.Objects;

public class Student {

    private final String group;
    private final double averageGrade;
    private final String recordBook;

    private Student(String group, double averageGrade, String recordBook) {
        this.group = group;
        this.averageGrade = averageGrade;
        this.recordBook = recordBook;
    }

    public static StudentBuilder builder() {
        return new StudentBuilder();
    }

    public String getGroup() {
        return group;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public String getRecordBook() {
        return recordBook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Student student = (Student) o;

        return Double.compare(averageGrade, student.averageGrade) == 0
                && Objects.equals(group, student.group)
                && Objects.equals(recordBook, student.recordBook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(group, averageGrade, recordBook);
    }

    @Override
    public String toString() {
        return "Student{" +
                "group='" + group + '\'' +
                ", averageGrade=" + averageGrade +
                ", recordBook='" + recordBook + '\'' +
                '}';
    }

    public static class StudentBuilder {

        private String group;
        private double averageGrade;
        private String recordBook;

        private StudentBuilder() {
        }

        public StudentBuilder group(String group) {
            this.group = group;
            return this;
        }

        public StudentBuilder averageGrade(double averageGrade) {
            this.averageGrade = averageGrade;
            return this;
        }

        public StudentBuilder recordBook(String recordBook) {
            this.recordBook = recordBook;
            return this;
        }

        public Student build() {
            return new Student(group, averageGrade, recordBook);
        }
    }
}
