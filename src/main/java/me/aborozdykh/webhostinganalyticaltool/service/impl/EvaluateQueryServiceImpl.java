package me.aborozdykh.webhostinganalyticaltool.service.impl;

import java.util.List;
import me.aborozdykh.webhostinganalyticaltool.entity.EvaluateQuery;
import me.aborozdykh.webhostinganalyticaltool.repository.EvaluateQueryRepository;
import me.aborozdykh.webhostinganalyticaltool.service.EvaluateQueryService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Andrii Borozdykh
 */
@Service
public class EvaluateQueryServiceImpl implements EvaluateQueryService {
    private final EvaluateQueryRepository evaluateQueryRepository;

    public EvaluateQueryServiceImpl(EvaluateQueryRepository evaluateQueryRepository) {
        this.evaluateQueryRepository = evaluateQueryRepository;
    }

    @Override
    public List<EvaluateQuery> saveAll(List<EvaluateQuery> evaluateQueryList) {
        return evaluateQueryRepository.saveAll(evaluateQueryList);
    }

    @Override
    public List<EvaluateQuery> findAll() {
        return evaluateQueryRepository.findAll();
    }

    @Override
    public Long getLastRecordNumber() {
        Pageable pageable = PageRequest.of(0, 1);
        List<EvaluateQuery> content
                = evaluateQueryRepository.findLastRecord(pageable).getContent();
        return content.isEmpty() ? 0L : content.get(0).getRecordNumber();
    }
}
