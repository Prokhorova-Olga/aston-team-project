package ru.aston.project.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.aston.project.model.Student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals("A-1,4.5,123", lines.get(0));
        assertEquals("A-2,4.6,124", lines.get(1));
        assertEquals("A-3,4.7,125", lines.get(2));
    }

    @Test
    void appendToFile_shouldNotFailForEmptyList(@TempDir Path tempDir) throws IOException {
        Path tempFile = tempDir.resolve("empty.txt");
        DataWriter writer = new DataWriter(tempFile.toString());

        writer.appendToFile(new ArrayList<>());

        assertTrue(Files.exists(tempFile));
        assertEquals(0, Files.readAllLines(tempFile).size());
    }

    @Test
    void appendToFile_shouldThrowForNullList(@TempDir Path tempDir) {
        Path tempFile = tempDir.resolve("null.txt");
        DataWriter writer = new DataWriter(tempFile.toString());

        assertThrows(NullPointerException.class, () -> writer.appendToFile(null));
    }
}
