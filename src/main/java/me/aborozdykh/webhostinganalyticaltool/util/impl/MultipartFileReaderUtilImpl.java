package me.aborozdykh.webhostinganalyticaltool.util.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import me.aborozdykh.webhostinganalyticaltool.util.FileReaderUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Andrii Borozdykh
 */
@Service
public class MultipartFileReaderUtilImpl implements FileReaderUtil {
    private static final java.lang.String TYPE = "text/csv";

    public boolean hasCorrectFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    @Override
    public List<String> getDataFromFile(InputStream inputStream) {
        try (BufferedReader fileReader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            List<String> records = new ArrayList<>();
            String record;
            while ((record = fileReader.readLine()) != null) {
                records.add(record);
            }
            records.remove(0);
            return records;
        } catch (IOException e) {
            throw new RuntimeException("Fail to read data from file: " + e.getMessage());
        }
    }
}

