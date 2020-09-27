package me.aborozdykh.webhostinganalyticaltool.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import me.aborozdykh.webhostinganalyticaltool.entity.AverageTime;
import me.aborozdykh.webhostinganalyticaltool.entity.Query;
import me.aborozdykh.webhostinganalyticaltool.entity.ResponseType;
import me.aborozdykh.webhostinganalyticaltool.entity.WaitingTimeLine;
import me.aborozdykh.webhostinganalyticaltool.service.AverageTimeService;
import org.springframework.stereotype.Service;

/**
 * @author Andrii Borozdykh
 */

@Service
public class AverageTimeServiceImpl implements AverageTimeService {
    private static final String STAR = "*";
    private static final String DASH = "-";

    @Override
    public List<AverageTime> getAverageTimeList(List<Query> queryList,
                                                List<WaitingTimeLine> waitingTimeLineList) {
        List<AverageTime> averageTimeList = new ArrayList<>();
        for (Query query : queryList) {
            var averageTime = new AverageTime();
            int time = 0;
            int count = 0;
            for (WaitingTimeLine waitingTimeLine : waitingTimeLineList) {
                if (waitingTimeLine.getRecordNumber() < query.getRecordNumber()
                        && serviceIsSame(waitingTimeLine.getServiceId(), query.getServiceId())
                        && questionIsSame(waitingTimeLine.getQuestion(), query.getQuestion())
                        && responseTypeIsSame(waitingTimeLine.getResponseType(),
                        query.getResponseType())
                        && dateIsBetween(waitingTimeLine.getDate(), query.getDateFrom(),
                        query.getDateTo())) {
                    time += waitingTimeLine.getTime();
                    count++;
                }
            }
            if (count != 0) {
                averageTime.setAverageTime(String.valueOf(time / count));
            } else {
                averageTime.setAverageTime(DASH);
            }
            averageTimeList.add(averageTime);
        }
        return averageTimeList;
    }

    private boolean serviceIsSame(String waitingTimeLineServiceId, String queryServiceId) {
        return queryServiceId.equals(STAR) || waitingTimeLineServiceId.startsWith(queryServiceId);
    }

    private boolean questionIsSame(String waitingTimeLineQuestion, String queryQuestion) {
        return queryQuestion.equals(STAR) || waitingTimeLineQuestion.startsWith(queryQuestion);
    }

    private boolean responseTypeIsSame(ResponseType waitingTimeLineResponseType,
                                       ResponseType queryResponseType) {
        return queryResponseType.equals(waitingTimeLineResponseType);
    }

    private boolean dateIsBetween(LocalDate waitingTimeLineDate,
                                  LocalDate queryDateFrom,
                                  LocalDate queryDateTo) {
        return waitingTimeLineDate.isAfter(queryDateFrom.minusDays(1))
                && waitingTimeLineDate.isBefore(queryDateTo.plusDays(1));
    }
}
