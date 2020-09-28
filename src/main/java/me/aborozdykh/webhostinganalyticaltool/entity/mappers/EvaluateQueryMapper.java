package me.aborozdykh.webhostinganalyticaltool.entity.mappers;

import me.aborozdykh.webhostinganalyticaltool.entity.EvaluateQuery;
import me.aborozdykh.webhostinganalyticaltool.entity.dto.RecordDto;
import org.springframework.stereotype.Component;

/**
 * @author Andrii Borozdykh
 */
@Component
public class EvaluateQueryMapper {
    public EvaluateQuery getQueryFromRecordDto(RecordDto recordDto) {
        var evaluateQuery = new EvaluateQuery();
        evaluateQuery.setServiceId(recordDto.getServiceId());
        evaluateQuery.setQuestion(recordDto.getQuestion());
        evaluateQuery.setResponseType(recordDto.getResponseType());
        evaluateQuery.setDateFrom(recordDto.getDateFrom());
        evaluateQuery.setDateTo(recordDto.getDateTo());
        return evaluateQuery;
    }

    public EvaluateQuery getQueryFromRecordDto(RecordDto recordDto, Long recordNumber) {
        var evaluateQuery = this.getQueryFromRecordDto(recordDto);
        evaluateQuery.setRecordNumber(recordNumber);
        return evaluateQuery;
    }
}
