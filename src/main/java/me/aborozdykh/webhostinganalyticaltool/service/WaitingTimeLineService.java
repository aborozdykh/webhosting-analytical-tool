package me.aborozdykh.webhostinganalyticaltool.service;

import java.util.List;
import me.aborozdykh.webhostinganalyticaltool.entity.WaitingTimeLine;

/**
 * @author Andrii Borozdykh
 */
public interface WaitingTimeLineService {
    WaitingTimeLine save(WaitingTimeLine waitingTimeLine);

    List<WaitingTimeLine> saveAll(List<WaitingTimeLine> waitingTimeLineList);

    List<WaitingTimeLine> findAll();
}
