package me.aborozdykh.webhostinganalyticaltool.repository;

import me.aborozdykh.webhostinganalyticaltool.entity.WaitingTimeLine;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Andrii Borozdykh
 */
public interface WaitingTimeLineRepository extends JpaRepository<WaitingTimeLine, Long> {
}
