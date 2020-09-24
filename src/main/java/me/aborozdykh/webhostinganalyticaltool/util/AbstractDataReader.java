package me.aborozdykh.webhostinganalyticaltool.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrii Borozdykh
 */
public class AbstractDataReader implements DataReader{
    @Override
    public List<String> getDataFromFile(InputStream inputStream) {
        try (BufferedReader fileReader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = fileReader.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } catch (IOException e) {
            throw new RuntimeException("Fail to read data from file: " + e.getMessage());
        }
    }
}
