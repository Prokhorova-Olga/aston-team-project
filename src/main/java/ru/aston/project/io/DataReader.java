package ru.aston.project.io;

import ru.aston.project.model.Student;

import java.util.List;

public class DataReader {

    private final FileDataReader fileDataReader;
    private final StudentParser studentParser;
    private final RandomDataGenerator randomDataGenerator;
    private final ConsoleDataReader consoleDataReader;

    public DataReader(FileDataReader fileDataReader, StudentParser studentParser, RandomDataGenerator randomDataGenerator, ConsoleDataReader consoleDataReader) {
        this.fileDataReader = fileDataReader;
        this.studentParser = studentParser;
        this.randomDataGenerator = randomDataGenerator;
        this.consoleDataReader = consoleDataReader;
    }

    public List<Student> readFromFile(String fileName) {
        List<String> lines = fileDataReader.readLinesFromResource(fileName);
        return studentParser.parseLines(lines);
    }

    public List<Student> generateRandom(int count) {
        return randomDataGenerator.generateRandom(count);
    }

    public List<Student> readFromConsole(int count) {
        return consoleDataReader.readFromConsole(count);
    }
}
