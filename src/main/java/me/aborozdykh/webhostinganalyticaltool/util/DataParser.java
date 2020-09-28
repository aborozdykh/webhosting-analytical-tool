package me.aborozdykh.webhostinganalyticaltool.util;

import java.util.List;
import me.aborozdykh.webhostinganalyticaltool.entity.EvaluateQuery;
import me.aborozdykh.webhostinganalyticaltool.entity.WaitingTimeLine;

/**
 * @author Andrii Borozdykh
 */
public interface DataParser {
    List<EvaluateQuery> getEvaluateQueryList(List<String> records);

    List<WaitingTimeLine> getWaitingTimeList(List<String> records);
}
