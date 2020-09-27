package me.aborozdykh.webhostinganalyticaltool.repository;

import me.aborozdykh.webhostinganalyticaltool.entity.Query;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Andrii Borozdykh
 */
public interface QueryRepository extends JpaRepository<Query, Long> {
}
