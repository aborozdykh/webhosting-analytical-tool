package me.aborozdykh.webhostinganalyticaltool.util.impl;

import java.util.ArrayList;
import java.util.List;
import me.aborozdykh.webhostinganalyticaltool.entity.Query;
import me.aborozdykh.webhostinganalyticaltool.entity.WaitingTimeLine;
import me.aborozdykh.webhostinganalyticaltool.entity.mappers.QueryMapper;
import me.aborozdykh.webhostinganalyticaltool.entity.mappers.RecordDtoMapper;
import me.aborozdykh.webhostinganalyticaltool.entity.mappers.WaitingTimeLineMapper;
import me.aborozdykh.webhostinganalyticaltool.util.DataParser;
import org.springframework.stereotype.Service;

/**
 * @author Andrii Borozdykh
 */
@Service
public class DataParserImpl implements DataParser {
    private final QueryMapper queryMapper;
    private final RecordDtoMapper recordDtoMapper;
    private final WaitingTimeLineMapper waitingTimeLineMapper;

    public DataParserImpl(QueryMapper queryMapper,
                          RecordDtoMapper recordDtoMapper,
                          WaitingTimeLineMapper waitingTimeLineMapper) {
        this.queryMapper = queryMapper;
        this.recordDtoMapper = recordDtoMapper;
        this.waitingTimeLineMapper = waitingTimeLineMapper;
    }

    @Override
    public List<Query> getQueryList(List<String> records) {
        List<Query> queryList = new ArrayList<>();
        var query = new Query();
        for (int i = 0; i < records.size(); i++) {
            var recordDto = recordDtoMapper.getRecordDtoFromRecord(records.get(i));
            if (recordDto.getRecordType().equals(RecordDtoMapper.QUERY_TYPE)) {
                query = queryMapper.getQueryFromRecordDto(recordDto, (long) i);
                queryList.add(query);
            }
        }
        return queryList;
    }

    @Override
    public List<WaitingTimeLine> getWaitingTimeList(List<String> records) {
        List<WaitingTimeLine> waitingTimeLineList = new ArrayList<>();
        var waitingTimeLine = new WaitingTimeLine();
        for (int i = 0; i < records.size(); i++) {
            var recordDto = recordDtoMapper.getRecordDtoFromRecord(records.get(i));
            if (recordDto.getRecordType().equals(RecordDtoMapper.WAITING_TIME_LINE_TYPE)) {
                waitingTimeLine = waitingTimeLineMapper
                        .getWaitingTimeLineFromRecordDto(recordDto, (long) i);
                waitingTimeLineList.add(waitingTimeLine);
            }
        }
        return waitingTimeLineList;
    }
}
