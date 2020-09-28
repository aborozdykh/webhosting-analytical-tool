package me.aborozdykh.webhostinganalyticaltool.util.impl;

import java.util.ArrayList;
import java.util.List;
import me.aborozdykh.webhostinganalyticaltool.entity.EvaluateQuery;
import me.aborozdykh.webhostinganalyticaltool.entity.WaitingTimeLine;
import me.aborozdykh.webhostinganalyticaltool.entity.mappers.EvaluateQueryMapper;
import me.aborozdykh.webhostinganalyticaltool.entity.mappers.RecordDtoMapper;
import me.aborozdykh.webhostinganalyticaltool.entity.mappers.WaitingTimeLineMapper;
import me.aborozdykh.webhostinganalyticaltool.service.EvaluateQueryService;
import me.aborozdykh.webhostinganalyticaltool.service.WaitingTimeLineService;
import me.aborozdykh.webhostinganalyticaltool.util.DataParser;
import org.springframework.stereotype.Service;

/**
 * @author Andrii Borozdykh
 */
@Service
public class DataParserImpl implements DataParser {
    private final EvaluateQueryMapper evaluateQueryMapper;
    private final RecordDtoMapper recordDtoMapper;
    private final WaitingTimeLineMapper waitingTimeLineMapper;
    private final EvaluateQueryService evaluateQueryService;
    private final WaitingTimeLineService waitingTimeLineService;

    public DataParserImpl(EvaluateQueryMapper evaluateQueryMapper,
                          RecordDtoMapper recordDtoMapper,
                          WaitingTimeLineMapper waitingTimeLineMapper,
                          EvaluateQueryService evaluateQueryService,
                          WaitingTimeLineService waitingTimeLineService) {
        this.evaluateQueryMapper = evaluateQueryMapper;
        this.recordDtoMapper = recordDtoMapper;
        this.waitingTimeLineMapper = waitingTimeLineMapper;
        this.evaluateQueryService = evaluateQueryService;
        this.waitingTimeLineService = waitingTimeLineService;
    }

    @Override
    public List<EvaluateQuery> getEvaluateQueryList(List<String> records) {
        List<EvaluateQuery> evaluateQueryList = new ArrayList<>();
        var evaluateQuery = new EvaluateQuery();
        var lastRecordNumber = getLastRecordNumber();
        for (long i = 0; i < records.size(); i++) {
            var recordDto = recordDtoMapper.getRecordDtoFromRecord(records.get((int) i));
            if (recordDto.getRecordType().equals(RecordDtoMapper.EVALUATE_QUERY_TYPE)) {
                evaluateQuery = evaluateQueryMapper.getQueryFromRecordDto(recordDto,
                        i + lastRecordNumber);
                evaluateQueryList.add(evaluateQuery);
            }
        }
        return evaluateQueryList;
    }

    @Override
    public List<WaitingTimeLine> getWaitingTimeList(List<String> records) {
        List<WaitingTimeLine> waitingTimeLineList = new ArrayList<>();
        var waitingTimeLine = new WaitingTimeLine();
        var lastRecordNumber = getLastRecordNumber();
        for (long i = 0; i < records.size(); i++) {
            var recordDto = recordDtoMapper.getRecordDtoFromRecord(records.get((int) i));
            if (recordDto.getRecordType().equals(RecordDtoMapper.WAITING_TIME_LINE_TYPE)) {
                waitingTimeLine = waitingTimeLineMapper
                        .getWaitingTimeLineFromRecordDto(recordDto, i + lastRecordNumber);
                waitingTimeLineList.add(waitingTimeLine);
            }
        }
        return waitingTimeLineList;
    }

    private long getLastRecordNumber() {
        var evaluateRecordNumber = evaluateQueryService.getLastRecordNumber();
        var waitingTimeLineRecordNumber = waitingTimeLineService.getLastRecordNumber();
        return evaluateRecordNumber >= waitingTimeLineRecordNumber ? evaluateRecordNumber
                : waitingTimeLineRecordNumber;
    }
}
