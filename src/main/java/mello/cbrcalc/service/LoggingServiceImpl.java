package mello.cbrcalc.service;

import mello.cbrcalc.dao.LoggingRepository;
import mello.cbrcalc.entity.LoggingEvent;
import mello.cbrcalc.entity.LoggingType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoggingServiceImpl implements LoggingService {
    @Autowired
    LoggingRepository loggingRepository;

    @Override
    public void saveLoggingEvent(LoggingEvent event) {
        loggingRepository.save(event);
    }

    @Override
    public List<LoggingEvent> findAllByUsernameOrderByLocalDateTimeDesc(String userName) {
        return loggingRepository.findAllByUsernameOrderByLocalDateTimeDesc(userName);
    }

    @Override
    public List<LoggingEvent> findAllByUsernameAndLoggingTypeOrderByLocalDateTimeDesc(String userName, LoggingType loggingType) {
        return loggingRepository.findAllByUsernameAndLoggingTypeOrderByLocalDateTimeDesc(userName, loggingType);
    }

    @Override
    public List<LoggingEvent> findAllByLoggingTypeOrderByLocalDateTimeDesc(LoggingType loggingType) {
        return loggingRepository.findAllByLoggingTypeOrderByLocalDateTimeDesc(loggingType);
    }
}
