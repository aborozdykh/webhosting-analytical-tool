package me.aborozdykh.webhostinganalyticaltool.util;

import java.io.InputStream;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Andrii Borozdykh
 */
public interface FileReaderUtil {
    List<String> getDataFromFile(InputStream inputStream);

    boolean hasCorrectFormat(MultipartFile file);
}
