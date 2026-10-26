package ru.aston.project.tests;

public class BuilderTest {
    public static void testBuilder() {

        /*        Код с заглушками        */

        Student student = new Student();

        if (student.group.equals("A-1") && student.averageGrade == 4.5) {
            System.out.println("Тест Builder ПРОЙДЕН.");
        } else System.out.println("Тест Builder ОШИБКА.");

        /*        Код для реальных данных        */

//        Student student = new StudentBuilder()
//                .setGroup("A-1")
//                .setAverageGrade(4.5)
//                .setRecordBook("123")
//                .build();
//        if (student.getGroup().equals("A-1") && student.getAverageGrade() == 4.5){
//            System.out.println("Тест Builder ПРОЙДЕН.");
//        } else System.out.println("Тест Builder ОШИБКА.");
    }
}
