package me.aborozdykh.webhostinganalyticaltool.util.impl;

import java.util.List;
import me.aborozdykh.webhostinganalyticaltool.util.FileReaderUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

/**
 * @author Andrii Borozdykh
 */
@SpringBootTest
class MultipartFileReaderUtilImplTest {
    @Autowired
    private FileReaderUtil fileReaderUtil;

    @Test
    void hasCorrectFormatIsTrue() {
        String content = "7\nC 1.1 8.15.1 P 15.10.2012 83";
        MockMultipartFile file
                = new MockMultipartFile("file",
                "testData1.csv",
                "text/csv",
                content.getBytes());
        Assert.assertTrue(fileReaderUtil.hasCorrectFormat(file));
    }

    @Test
    public void hasCorrectFormatIsFalse() {
        String content = "7\nC 1.1 8.15.1 P 15.10.2012 83";
        MockMultipartFile file
                = new MockMultipartFile("file",
                "testData1.csv",
                "text/plain",
                content.getBytes());
        Assert.assertFalse(fileReaderUtil.hasCorrectFormat(file));
    }

    @Test
    void getDataFromFileIsOk() {
        var inputStream = getClass().getClassLoader().getResourceAsStream("testData1.csv");
        var actualLineList = List.of("C 1.1 8.15.1 P 15.10.2012 83");
        var expectedLineList = fileReaderUtil.getDataFromFile(inputStream);

        Assert.assertEquals(actualLineList, expectedLineList);
    }
}
