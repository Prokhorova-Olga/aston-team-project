package ru.aston.project.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.aston.project.model.Student;
import ru.aston.project.validator.StudentValidatorService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataReaderTest {
    @Test
    void generateRandom_size() {
        StudentValidatorService validatorService = new StudentValidatorService();

        DataReader dataReader = new DataReader(validatorService);
        List<Student> students = dataReader.generateRandom(10);

        int size1 = 10;
        int size2 = students.size();

        assertEquals(size1, size2);
    }

    @Test
    void generateRandom_averageGrade1to5() {
        StudentValidatorService validatorService = new StudentValidatorService();

        DataReader dataReader = new DataReader(validatorService);
        List<Student> students = dataReader.generateRandom(10);

        for (Student student : students) {
            assertTrue(student.getAverageGrade() >= 1.0);
            assertTrue(student.getAverageGrade() <= 5.0);
        }
    }

    @Test
    void generateRandom_illegalArgumentException() {
        StudentValidatorService validatorService = new StudentValidatorService();
        DataReader dataReader = new DataReader(validatorService);

        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    dataReader.generateRandom(-1);
                }
        );
    }

    @Test
    void generateRandom_count0() {
        StudentValidatorService validatorService = new StudentValidatorService();

        DataReader dataReader = new DataReader(validatorService);
        List<Student> students = dataReader.generateRandom(0);

        assertTrue(students.isEmpty());
    }

    @Test
    void readFromFile_creating(@TempDir Path tempDir) throws IOException {
        Path tempFile = tempDir.resolve("test-file.txt");
        Files.write(tempFile, List.of(
                "A-1, 4.5, 123",
                "A-2, 4.6, 124",
                "A-3, 4.7, 125"
        ));

        StudentValidatorService validatorService = new StudentValidatorService();
        DataReader dataReader = new DataReader(validatorService);
        List<Student> students = dataReader.readFromFile(tempFile.toString());

        assertEquals(3, students.size());
        assertEquals("A-1", students.get(0).getGroup());
        assertEquals(4.5, students.get(0).getAverageGrade());
        assertEquals("123", students.get(0).getRecordBook());
    }

    @Test
    void readFromFile_FileDoesNotExist(@TempDir Path tempDir) {
        Path missingFile = tempDir.resolve("no-such-file.txt");

        StudentValidatorService validatorService = new StudentValidatorService();
        DataReader dataReader = new DataReader(validatorService);

        List<Student> students = assertDoesNotThrow(
                () -> dataReader.readFromFile(missingFile.toString()));

        assertTrue(students.isEmpty());
    }

    @Test
    void readFromFile_invalidLine(@TempDir Path tempDir) throws IOException {
        Path tempFile = tempDir.resolve("broken-line.txt");
        Files.writeString(tempFile, "A-1, 4.5");

        StudentValidatorService validatorService = new StudentValidatorService();
        DataReader dataReader = new DataReader(validatorService);
        List<Student> students = dataReader.readFromFile(tempFile.toString());

        assertTrue(students.isEmpty());
    }

    @Test
    void readFromFile_invalidAverageGrade(@TempDir Path tempDir) throws IOException {
        Path tempFile = tempDir.resolve("invalid-grade.txt");
        Files.writeString(tempFile, "A-1, 10.0, 123");

        StudentValidatorService validatorService = new StudentValidatorService();
        DataReader dataReader = new DataReader(validatorService);
        List<Student> students = dataReader.readFromFile(tempFile.toString());

        assertTrue(students.isEmpty());
    }

    @Test
    void readFromFile_shouldReturnEmptyListForEmptyFile(@TempDir Path tempDir) throws IOException {
        Path emptyFile = Files.createFile(tempDir.resolve("empty.txt"));
        DataReader reader = new DataReader(new StudentValidatorService());

        List<Student> students = assertDoesNotThrow(() -> reader.readFromFile(emptyFile.toString()));

        assertNotNull(students);
        assertEquals(0, students.size());
    }
}
