package ru.aston.project.io;

import ru.aston.project.model.Student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDataReader {

    public List<Student> readFromFileWithStreams(String path) throws IOException {
        try (Stream<String> lines = Files.lines(Path.of(path))) {
            return lines
                    .filter(line -> {
                        String[] parts = line.split(",");
                        return parts.length == 3 && parts[1].trim().matches("\\d+(\\.\\d+)?");
                    })
                    .map(line -> line.split(","))
                    .map(parts -> Student.builder()
                            .group(parts[0].trim())
                            .averageGrade(Double.parseDouble(parts[1].trim()))
                            .recordBook(parts[2].trim())
                            .build())
                    .collect(Collectors.toList());
        }
    }
}
