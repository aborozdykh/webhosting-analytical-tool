package me.aborozdykh.webhostinganalyticaltool.service.impl;

import java.util.ArrayList;
import me.aborozdykh.webhostinganalyticaltool.entity.AverageTime;
import me.aborozdykh.webhostinganalyticaltool.service.AverageTimeService;
import me.aborozdykh.webhostinganalyticaltool.util.DataParser;
import me.aborozdykh.webhostinganalyticaltool.util.FileReaderUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Andrii Borozdykh
 */
@SpringBootTest
class AverageTimeServiceImplTest {
    @Autowired
    private AverageTimeService averageTimeService;
    @Autowired
    private DataParser dataParser;
    @Autowired
    private FileReaderUtil fileReaderUtil;

    @Test
    void getAverageTimeList() {
        var expected = new ArrayList<AverageTime>();
        expected.add(new AverageTime("83"));
        expected.add(new AverageTime("100"));
        expected.add(new AverageTime("-"));
        var inputStream = getClass().getClassLoader().getResourceAsStream("testData.csv");
        var records = fileReaderUtil.getDataFromFile(inputStream);
        var queryList = dataParser.getQueryList(records);
        var waitingTimeList = dataParser.getWaitingTimeList(records);
        var actual = averageTimeService.getAverageTimeList(queryList, waitingTimeList);
        Assert.assertEquals(expected, actual);
    }
}
