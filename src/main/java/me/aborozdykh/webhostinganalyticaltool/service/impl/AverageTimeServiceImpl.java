package me.aborozdykh.webhostinganalyticaltool.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import me.aborozdykh.webhostinganalyticaltool.entity.AverageTime;
import me.aborozdykh.webhostinganalyticaltool.entity.EvaluateQuery;
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
    public List<AverageTime> getAverageTimeList(List<EvaluateQuery> evaluateQueryList,
                                                List<WaitingTimeLine> waitingTimeLineList) {
        List<AverageTime> averageTimeList = new ArrayList<>();
        for (EvaluateQuery evaluateQuery : evaluateQueryList) {
            var averageTime = new AverageTime();
            int time = 0;
            int count = 0;
            for (WaitingTimeLine waitingTimeLine : waitingTimeLineList) {
                if (waitingTimeLine.getRecordNumber() < evaluateQuery.getRecordNumber()
                        && serviceIsSame(waitingTimeLine.getServiceId(),
                        evaluateQuery.getServiceId())
                        && questionIsSame(waitingTimeLine.getQuestion(),
                        evaluateQuery.getQuestion())
                        && responseTypeIsSame(waitingTimeLine.getResponseType(),
                        evaluateQuery.getResponseType())
                        && dateIsBetween(waitingTimeLine.getDate(), evaluateQuery.getDateFrom(),
                        evaluateQuery.getDateTo())) {
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

    private boolean serviceIsSame(String waitingTimeLineServiceId, String evaluateQueryServiceId) {
        return evaluateQueryServiceId.equals(STAR)
                || waitingTimeLineServiceId.startsWith(evaluateQueryServiceId);
    }

    private boolean questionIsSame(String waitingTimeLineQuestion, String evaluateQueryQuestion) {
        return evaluateQueryQuestion.equals(STAR)
                || waitingTimeLineQuestion.startsWith(evaluateQueryQuestion);
    }

    private boolean responseTypeIsSame(ResponseType waitingTimeLineResponseType,
                                       ResponseType evaluateQueryResponseType) {
        return evaluateQueryResponseType.equals(waitingTimeLineResponseType);
    }

    private boolean dateIsBetween(LocalDate waitingTimeLineDate,
                                  LocalDate evaluateQueryDateFrom,
                                  LocalDate evaluateQueryDateTo) {
        return waitingTimeLineDate.isAfter(evaluateQueryDateFrom.minusDays(1))
                && waitingTimeLineDate.isBefore(evaluateQueryDateTo.plusDays(1));
    }
}
