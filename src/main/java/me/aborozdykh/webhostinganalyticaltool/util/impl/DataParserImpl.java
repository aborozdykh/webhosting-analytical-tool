package me.aborozdykh.webhostinganalyticaltool.util.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import me.aborozdykh.webhostinganalyticaltool.entity.Query;
import me.aborozdykh.webhostinganalyticaltool.entity.WaitingTimeLine;
import me.aborozdykh.webhostinganalyticaltool.entity.dto.RecordDto;
import me.aborozdykh.webhostinganalyticaltool.entity.mappers.QueryMapper;
import me.aborozdykh.webhostinganalyticaltool.entity.mappers.RecordDtoMapper;
import me.aborozdykh.webhostinganalyticaltool.entity.mappers.WaitingTimeLineMapper;
import me.aborozdykh.webhostinganalyticaltool.util.DataParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Andrii Borozdykh
 */
@Service
public class DataParserImpl implements DataParser {

    @Autowired
    private final QueryMapper queryMapper;
    @Autowired
    private final RecordDtoMapper recordDtoMapper;
    @Autowired
    private final WaitingTimeLineMapper waitingTimeLineMapper;

    public DataParserImpl(QueryMapper queryMapper,
                          RecordDtoMapper recordDtoMapper,
                          WaitingTimeLineMapper waitingTimeLineMapper) {
        this.queryMapper = queryMapper;
        this.recordDtoMapper = recordDtoMapper;
        this.waitingTimeLineMapper = waitingTimeLineMapper;
    }

    @Override
    public List<RecordDto> getRecordDtoList(List<String> records) {
        return records.stream()
                .map(recordDtoMapper::getRecordDtoFromRecord)
                .collect(Collectors.toList());
    }

    public List<Query> getQueryList(List<RecordDto> recordDtoList) {
        return recordDtoList.stream()
                .map(queryMapper::getQueryFromRecordDto)
                .collect(Collectors.toList());
    }

    public List<Query> getQueryListFromRecords(List<String> records) {
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
    public List<WaitingTimeLine> getWaitingTimeListFromRecords(List<String> records) {
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
