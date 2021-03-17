package mello.cbrcalc.dao;

import mello.cbrcalc.entity.LoggingEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggingRepository extends JpaRepository<LoggingEvent, Integer> {
}
