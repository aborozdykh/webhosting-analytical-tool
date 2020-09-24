package me.aborozdykh.webhostinganalyticaltool.util.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import me.aborozdykh.webhostinganalyticaltool.entity.dto.ObjectToDto;
import me.aborozdykh.webhostinganalyticaltool.util.AbstractDataReader;
import me.aborozdykh.webhostinganalyticaltool.util.DataReader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Andrii Borozdykh
 */
@Service
public class MultipartFileReader extends AbstractDataReader{
    private static final java.lang.String TYPE = "text/csv";

    public boolean hasCorrectFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }
}

