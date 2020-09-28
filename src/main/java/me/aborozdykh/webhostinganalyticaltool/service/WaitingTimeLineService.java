package me.aborozdykh.webhostinganalyticaltool.service;

import java.util.List;
import me.aborozdykh.webhostinganalyticaltool.entity.WaitingTimeLine;

/**
 * @author Andrii Borozdykh
 */
public interface WaitingTimeLineService {
    List<WaitingTimeLine> saveAll(List<WaitingTimeLine> waitingTimeLineList);

    List<WaitingTimeLine> findAll();

    Long getLastRecordNumber();
}
