package me.aborozdykh.webhostinganalyticaltool.util;

import java.util.List;
import me.aborozdykh.webhostinganalyticaltool.entity.Query;
import me.aborozdykh.webhostinganalyticaltool.entity.WaitingTimeLine;
import me.aborozdykh.webhostinganalyticaltool.entity.dto.RecordDto;

/**
 * @author Andrii Borozdykh
 */
public interface DataParser {
    List<RecordDto> getRecordDtoList(List<String> records);

    List<Query> getQueryList(List<RecordDto> recordDtoList);

    List<Query> getQueryListFromRecords(List<String> records);

    List<WaitingTimeLine> getWaitingTimeListFromRecords(List<String> records);
}
