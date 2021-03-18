package mello.cbrcalc.dao;

import mello.cbrcalc.entity.LoggingEvent;
import mello.cbrcalc.entity.LoggingType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoggingRepository extends JpaRepository<LoggingEvent, Integer> {
   List<LoggingEvent> findAllByUsernameOrderByLocalDateTimeDesc(String userName);
   List<LoggingEvent> findAllByUsernameAndLoggingTypeOrderByLocalDateTimeDesc(String userName, LoggingType loggingType);
   List<LoggingEvent> findAllByLoggingTypeOrderByLocalDateTimeDesc(LoggingType loggingType);
}
