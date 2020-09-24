package me.aborozdykh.webhostinganalyticaltool.util;

import java.io.InputStream;
import java.util.List;
import me.aborozdykh.webhostinganalyticaltool.entity.dto.ObjectToDto;

/**
 * @author Andrii Borozdykh
 */
public interface DataReader {
    List<String> getDataFromFile(InputStream inputStream);
}
