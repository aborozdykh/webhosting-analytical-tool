package me.aborozdykh.webhostinganalyticaltool.service;

import java.util.List;
import me.aborozdykh.webhostinganalyticaltool.entity.EvaluateQuery;

/**
 * @author Andrii Borozdykh
 */
public interface EvaluateQueryService {
    List<EvaluateQuery> saveAll(List<EvaluateQuery> evaluateQueryList);

    List<EvaluateQuery> findAll();

    Long getLastRecordNumber();
}
