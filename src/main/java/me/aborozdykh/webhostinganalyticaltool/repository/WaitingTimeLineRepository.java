package me.aborozdykh.webhostinganalyticaltool.repository;

import me.aborozdykh.webhostinganalyticaltool.entity.WaitingTimeLine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Andrii Borozdykh
 */
public interface WaitingTimeLineRepository extends JpaRepository<WaitingTimeLine, Long> {
    @Query("SELECT new me.aborozdykh.webhostinganalyticaltool.entity.WaitingTimeLine("
            + "w.id, w.recordNumber, w.serviceId, w.question, w.responseType, w.date, w.time) "
            + "FROM WaitingTimeLine w "
            + "ORDER BY w.id DESC")
    Page<WaitingTimeLine> findLastRecord(Pageable pageable);
}
