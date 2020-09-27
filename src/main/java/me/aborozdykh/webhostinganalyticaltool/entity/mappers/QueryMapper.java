package me.aborozdykh.webhostinganalyticaltool.entity.mappers;

import me.aborozdykh.webhostinganalyticaltool.entity.Query;
import me.aborozdykh.webhostinganalyticaltool.entity.dto.RecordDto;
import org.springframework.stereotype.Component;

/**
 * @author Andrii Borozdykh
 */
@Component
public class QueryMapper {
    public Query getQueryFromRecordDto(RecordDto recordDto) {
        var query = new Query();
        query.setServiceId(recordDto.getService());
        query.setQuestion(recordDto.getQuestion());
        query.setResponseType(recordDto.getResponseType());
        query.setDateFrom(recordDto.getDateFrom());
        query.setDateTo(recordDto.getDateTo());
        return query;
    }

    public Query getQueryFromRecordDto(RecordDto recordDto, Long recordNumber) {
        var query = this.getQueryFromRecordDto(recordDto);
        query.setRecordNumber(recordNumber);
        return query;
    }
}
