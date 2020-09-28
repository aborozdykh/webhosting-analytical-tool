package me.aborozdykh.webhostinganalyticaltool.repository;

import me.aborozdykh.webhostinganalyticaltool.entity.EvaluateQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Andrii Borozdykh
 */
public interface EvaluateQueryRepository extends JpaRepository<EvaluateQuery, Long> {
    @Query("SELECT new me.aborozdykh.webhostinganalyticaltool.entity.EvaluateQuery("
            + "eq.id, eq.serviceId, eq.question, eq.responseType, eq.dateFrom, "
            + "eq.dateTo, eq.recordNumber) "
            + "FROM EvaluateQuery eq "
            + "ORDER BY eq.id DESC")
    Page<EvaluateQuery> findLastRecord(Pageable pageable);
}
