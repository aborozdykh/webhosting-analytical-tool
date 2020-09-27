package me.aborozdykh.webhostinganalyticaltool.service;

import java.util.List;
import me.aborozdykh.webhostinganalyticaltool.entity.AverageTime;
import me.aborozdykh.webhostinganalyticaltool.entity.Query;
import me.aborozdykh.webhostinganalyticaltool.entity.WaitingTimeLine;

/**
 * @author Andrii Borozdykh
 */
public interface AverageTimeService {
    List<AverageTime> getAverageTimeList(List<Query> queryList,
                                         List<WaitingTimeLine> waitingTimeLineList);
}
