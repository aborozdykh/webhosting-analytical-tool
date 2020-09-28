package me.aborozdykh.webhostinganalyticaltool.util.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import me.aborozdykh.webhostinganalyticaltool.entity.EvaluateQuery;
import me.aborozdykh.webhostinganalyticaltool.entity.ResponseType;
import me.aborozdykh.webhostinganalyticaltool.entity.WaitingTimeLine;
import me.aborozdykh.webhostinganalyticaltool.util.DataParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 * @author Andrii Borozdykh
 */
@SpringBootTest
class DataParserImplTest {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Autowired
    private DataParser dataParser;

    @Test
    void getQueryList() {
        var evaluateQuery = new EvaluateQuery();
        evaluateQuery.setRecordNumber(1L);
        evaluateQuery.setServiceId("1.1");
        evaluateQuery.setQuestion("8");
        evaluateQuery.setResponseType(ResponseType.P);
        evaluateQuery.setDateFrom(LocalDate.parse("01.01.2012", FORMATTER));
        evaluateQuery.setDateTo(LocalDate.parse("01.12.2012", FORMATTER));
        List<EvaluateQuery> expected = new ArrayList<>();
        expected.add(evaluateQuery);
        var actual = dataParser.getEvaluateQueryList(getRecords());
        Assert.assertEquals(expected, actual);
    }

    @Test
    void getWaitingTimeList() {
        var waitingTimeLine = new WaitingTimeLine();
        waitingTimeLine.setRecordNumber(0L);
        waitingTimeLine.setServiceId("1.1");
        waitingTimeLine.setQuestion("5.5.1");
        waitingTimeLine.setResponseType(ResponseType.P);
        waitingTimeLine.setDate(LocalDate.parse("01.11.2012", FORMATTER));
        waitingTimeLine.setTime(Integer.parseInt("117"));
        List<WaitingTimeLine> expected = new ArrayList<>();
        expected.add(waitingTimeLine);
        var actual = dataParser.getWaitingTimeList(getRecords());
        Assert.assertEquals(expected, actual);
    }

    private List<String> getRecords() {
        List<String> records = new ArrayList<>();
        records.add("C 1.1 5.5.1 P 01.11.2012 117");
        records.add("D 1.1 8 P 01.01.2012-01.12.2012");
        return records;
    }
}
