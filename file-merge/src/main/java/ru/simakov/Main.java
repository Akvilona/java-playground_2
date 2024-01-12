package ru.simakov;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Main {
    public static void main(final String[] args) throws IOException {
        // Determining the path for a new file
        Path newFilePath = Paths.get("Color Matte_ru_en.txt");

        // If the file exists, delete it
        Files.deleteIfExists(newFilePath);

        // Creating a new file
        Files.createFile(newFilePath);

        // Opening the stream to read from files in English and Russian
        try (BufferedReader readerEn = Files.newBufferedReader(Paths.get("C:\\Java\\java-playground_2\\Color_Matte.srt"), StandardCharsets.UTF_8);
             BufferedReader readerRu = Files.newBufferedReader(Paths.get("C:\\Java\\java-playground_2\\Color_Matte_ru.txt"), StandardCharsets.UTF_8)) {

            // Cycle counter
            int cycleCount = 0;

            // Reading lines from both files
            String lineEn;
            String lineRu;
            while ((lineEn = readerEn.readLine()) != null && (lineRu = readerRu.readLine()) != null) {
                // Forming a string to write to a new file
                String string = lineEn.isEmpty() || Character.isDigit(lineEn.charAt(0))
                                ? lineEn + System.lineSeparator()
                                : lineRu.replaceAll("[,.]", "") + " - " + lineEn.replaceAll("[,.]", "") + System.lineSeparator();

                // Writing a line to a new file
                Files.writeString(newFilePath, string, StandardCharsets.UTF_8, StandardOpenOption.APPEND);

                // Output of a point to the console every five thousandth cycle
                if (++cycleCount % 5000 == 0) {
                    System.out.print(".");
                }
            }
            // Line feed after completion of the loop
            System.out.println("\nTotal lines: " + cycleCount );
        }
    }
}
