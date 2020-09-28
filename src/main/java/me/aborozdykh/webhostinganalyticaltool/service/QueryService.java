package me.aborozdykh.webhostinganalyticaltool.service;

import java.util.List;
import me.aborozdykh.webhostinganalyticaltool.entity.Query;

/**
 * @author Andrii Borozdykh
 */
public interface QueryService {
    List<Query> saveAll(List<Query> queryList);

    List<Query> findAll();
}
