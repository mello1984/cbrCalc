package mello.cbrcalc.service;

import mello.cbrcalc.dao.LoggingRepository;
import mello.cbrcalc.entity.LoggingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoggingServiceImpl implements LoggingService {
    @Autowired
    LoggingRepository loggingRepository;

    @Override
    public void saveLoggingEvent(LoggingEvent event) {
        loggingRepository.save(event);
    }
}
