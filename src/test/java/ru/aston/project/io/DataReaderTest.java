package ru.aston.project.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aston.project.validator.StudentValidatorService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DataReaderTest {

    private DataReader dataReader;

    @BeforeEach
    void setUp() {
        StudentValidatorService validatorService = new StudentValidatorService();
        dataReader = new DataReader(
                new FileDataReader(),
                new StudentParser(validatorService),
                new RandomDataGenerator(),
                new ConsoleDataReader(validatorService)
        );
    }

    @Test
    void readFromFile_shouldReturnList() {
        List<?> students = dataReader.readFromFile("students.txt");
        assertNotNull(students);
        assertFalse(students.isEmpty());
    }
}
