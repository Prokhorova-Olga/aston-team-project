package ru.aston.project.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.aston.project.model.Student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataWriterTest {
    @Test
    void appendToFile(@TempDir Path tempDir) throws IOException {
        Path tempFile = tempDir.resolve("students.txt");
        DataWriter writer = new DataWriter(tempFile.toString());

        Student s1 = Student.builder().group("A-1").averageGrade(4.5).recordBook("123").build();
        Student s2 = Student.builder().group("A-2").averageGrade(4.6).recordBook("124").build();
        Student s3 = Student.builder().group("A-3").averageGrade(4.7).recordBook("125").build();

        writer.appendToFile(List.of(s1, s2));
        writer.appendToFile(List.of(s3));

        List<String> lines = Files.readAllLines(tempFile);

        assertEquals(3, lines.size());
    }
}
