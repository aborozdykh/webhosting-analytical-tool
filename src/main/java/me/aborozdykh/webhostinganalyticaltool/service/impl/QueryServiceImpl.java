package me.aborozdykh.webhostinganalyticaltool.service.impl;

import java.util.List;
import me.aborozdykh.webhostinganalyticaltool.entity.Query;
import me.aborozdykh.webhostinganalyticaltool.repository.QueryRepository;
import me.aborozdykh.webhostinganalyticaltool.service.QueryService;
import org.springframework.stereotype.Service;

/**
 * @author Andrii Borozdykh
 */
@Service
public class QueryServiceImpl implements QueryService {
    private final QueryRepository queryRepository;

    public QueryServiceImpl(QueryRepository queryRepository) {
        this.queryRepository = queryRepository;
    }

    @Override
    public List<Query> saveAll(List<Query> queryList) {
        return queryRepository.saveAll(queryList);
    }

    @Override
    public List<Query> findAll() {
        return queryRepository.findAll();
    }
}
