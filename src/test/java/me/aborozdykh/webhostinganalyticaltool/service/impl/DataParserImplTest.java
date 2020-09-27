package me.aborozdykh.webhostinganalyticaltool.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import me.aborozdykh.webhostinganalyticaltool.entity.WaitingTimeLine;
import me.aborozdykh.webhostinganalyticaltool.entity.dto.RecordDto;
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

    @Autowired
    private DataParser dataParser;

    @Test
    void getFileLIneDtoList() {
        List<String> lines = new ArrayList<>();
        lines.add("C 1.1 5.5.1 P 01.11.2012 117");
        lines.add("D 1.1 8 P 01.01.2012-01.12.2012");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        RecordDto recordDtoC = new RecordDto();
        recordDtoC.setRecordType("C");
        recordDtoC.setService("1.1");
        recordDtoC.setQuestion("5.5.1");
        recordDtoC.setAnswer(WaitingTimeLine.Answer.valueOf("P"));
        recordDtoC.setDate(LocalDate.parse("01.11.2012", formatter));
        recordDtoC.setTime(Integer.parseInt("117"));

        RecordDto recordDtoD = new RecordDto();
        recordDtoD.setRecordType("D");
        recordDtoD.setService("1.1");
        recordDtoD.setQuestion("8");
        recordDtoD.setAnswer(WaitingTimeLine.Answer.valueOf("P"));
        recordDtoD.setDateFrom(LocalDate.parse("01.01.2012", formatter));
        recordDtoD.setDateTo(LocalDate.parse("01.12.2012", formatter));

        List<RecordDto> expected = new ArrayList<>();
        expected.add(recordDtoC);
        expected.add(recordDtoD);

        var actual = dataParser.getRecordDtoList(lines);

        Assert.assertEquals(expected, actual);
    }
}
