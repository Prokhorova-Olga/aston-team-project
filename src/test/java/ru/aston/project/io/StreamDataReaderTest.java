package ru.aston.project.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.aston.project.model.Student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StreamDataReaderTest {

    private final StreamDataReader reader = new StreamDataReader();

    @Test
    void readFromFile_shouldReturnAllStudents_whenLinesAreValid(@TempDir Path tempDir) throws IOException {
        Path tempFile = tempDir.resolve("students.txt");
        Files.write(tempFile, List.of(
                "A-1, 4.5, 123",
                "A-2, 4.6, 124",
                "A-3, 4.7, 125"
        ));

        List<Student> students = reader.readFromFileWithStreams(tempFile.toString());

        assertEquals(3, students.size());
        assertEquals(
                Student.builder().group("A-1").averageGrade(4.5).recordBook("123").build(),
                students.get(0));
        assertEquals(
                Student.builder().group("A-2").averageGrade(4.6).recordBook("124").build(),
                students.get(1));
        assertEquals(
                Student.builder().group("A-3").averageGrade(4.7).recordBook("125").build(),
                students.get(2));
    }

    @Test
    void readFromFile_shouldSkipLine_whenInvalidGrade(@TempDir Path tempDir) throws IOException {
        Path tempFile = tempDir.resolve("broken-grade.txt");
        Files.writeString(tempFile, "A-1, ОШИБКА, 123");

        List<Student> students = assertDoesNotThrow(
                () -> reader.readFromFileWithStreams(tempFile.toString()));

        assertEquals(0, students.size());
    }

    @Test
    void readFromFile_shouldSkipLine_whenNotThreeElements(@TempDir Path tempDir) throws IOException {
        Path tempFile = tempDir.resolve("broken-line.txt");
        Files.writeString(tempFile, "A-1, 4.5");

        List<Student> students = reader.readFromFileWithStreams(tempFile.toString());

        assertEquals(0, students.size());
    }

    @Test
    void readFromFile_shouldReturnEmptyList_whenFileIsEmpty(@TempDir Path tempDir) throws IOException {
        Path tempFile = tempDir.resolve("empty.txt");
        Files.createFile(tempFile);

        List<Student> students = reader.readFromFileWithStreams(tempFile.toString());

        assertTrue(students.isEmpty());
    }
}