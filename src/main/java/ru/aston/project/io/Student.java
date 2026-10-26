package ru.aston.project.io;

public class Student {
    private String group;
    private  double averageGrade;
    private String recordBook;

    public Student() {

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

     public  void setGroup(String group) {
        this.group = group;
     }

     public  void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
     }

     public  void setRecordBook(String recordBook) {
        this.recordBook = recordBook;
     }
}
