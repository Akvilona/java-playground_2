package ru.simakov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

@SuppressWarnings("PMD.AssignmentInOperand")
public class Main {
    public static void main(final String[] args) throws IOException {
        Path newFilePath = Paths.get("Color Matte_ru_en.txt");
        if (Files.exists(newFilePath)) {
            Files.delete(newFilePath);
        }

        Files.createFile(newFilePath);

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        try (InputStream inputStreamEn = Files.newInputStream(Paths.get("C:\\Java\\java-playground\\Color_Matte.srt"));
             InputStreamReader streamReaderEn = new InputStreamReader(Objects.requireNonNull(inputStreamEn), StandardCharsets.UTF_8);
             BufferedReader readerEn = new BufferedReader(streamReaderEn);

             InputStream inputStreamRu = Files.newInputStream(Paths.get("C:\\Java\\java-playground\\Color_Matte_ru.txt"));
             InputStreamReader streamReaderRu = new InputStreamReader(Objects.requireNonNull(inputStreamRu), StandardCharsets.UTF_8);
             BufferedReader readerRu = new BufferedReader(streamReaderRu)) {

            String lineEn;
            String lineRu;

            while ((lineEn = readerEn.readLine()) != null && (lineRu = readerRu.readLine()) != null) {
                String string = lineEn.isEmpty() || Character.isDigit(lineEn.charAt(0))
                                ? lineEn + System.lineSeparator()
                                : lineRu.replaceAll(",","").replaceAll("\\.", "")
                                    + " - "
                                    + lineEn.replaceAll(",","").replaceAll("\\.", "")
                                    + System.lineSeparator();

                Files.writeString(newFilePath, string, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            }
        }
    }
}
