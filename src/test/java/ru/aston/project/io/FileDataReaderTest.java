package ru.aston.project.io;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileDataReaderTest {

    private final FileDataReader dataReader = new FileDataReader();

    @Test
    void readLinesFromResource_shouldReturnLines_whenFileExists() {
        List<String> lines = dataReader.readLinesFromResource("students.txt");
        assertFalse(lines.isEmpty());
        assertTrue(lines.get(0).contains("A-1"));
    }

    @Test
    void readLinesFromResource_shouldReturnEmptyList_whenFileNotFound() {
        List<String> lines = dataReader.readLinesFromResource("не_найден_file.txt");
        assertTrue(lines.isEmpty());
    }
}
