package mello.cbrcalc.service;

import mello.cbrcalc.entity.LoggingEvent;
import mello.cbrcalc.entity.LoggingType;

import java.util.List;

public interface LoggingService {
   void saveLoggingEvent(LoggingEvent event);
   List<LoggingEvent> findAllByUsernameOrderByLocalDateTimeDesc(String userName);
   List<LoggingEvent> findAllByLoggingTypeOrderByLocalDateTimeDesc(LoggingType loggingType);
   List<LoggingEvent> findAllByUsernameAndLoggingTypeOrderByLocalDateTimeDesc(String userName, LoggingType loggingType);
}
