package me.aborozdykh.webhostinganalyticaltool.entity.mappers;

import me.aborozdykh.webhostinganalyticaltool.entity.WaitingTimeLine;
import me.aborozdykh.webhostinganalyticaltool.entity.dto.RecordDto;
import org.springframework.stereotype.Component;

/**
 * @author Andrii Borozdykh
 */
@Component
public class WaitingTimeLineMapper {
    public WaitingTimeLine getWaitingTimeLineFromRecordDto(RecordDto recordDto) {
        var waitingTimeLine = new WaitingTimeLine();
        waitingTimeLine.setServiceId(recordDto.getServiceId());
        waitingTimeLine.setQuestion(recordDto.getQuestion());
        waitingTimeLine.setResponseType(recordDto.getResponseType());
        waitingTimeLine.setDate(recordDto.getDate());
        waitingTimeLine.setTime(recordDto.getTime());
        return waitingTimeLine;
    }

    public WaitingTimeLine getWaitingTimeLineFromRecordDto(RecordDto recordDto, Long recordNumber) {
        var waitingTimeLine = this.getWaitingTimeLineFromRecordDto(recordDto);
        waitingTimeLine.setRecordNumber(recordNumber);
        return waitingTimeLine;
    }
}
