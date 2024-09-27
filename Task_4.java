package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Task_4 {

    // Define a functional interface for behavior parametrization
    @FunctionalInterface
    public interface BufferedReaderProcessor {
        String process(BufferedReader br) throws IOException;
    }

    // The 'processFile' method which wraps resource handling
    public static String processFile(BufferedReaderProcessor processor) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("src/test.txt"))) {
            return processor.process(br);
        }
    }

    public static void main(String[] args) throws IOException {
        // Pass a lambda to read the first line
        String firstLine = processFile(BufferedReader::readLine);
        System.out.println("First line: " + firstLine);

        // Pass a lambda to count the number of lines in the file
        String numLines = processFile(br -> {
            int lines = 0;
            while (br.readLine() != null) {
                lines++;
            }
            return "Number of lines: " + lines;
        });
        System.out.println(numLines);
    }
}
