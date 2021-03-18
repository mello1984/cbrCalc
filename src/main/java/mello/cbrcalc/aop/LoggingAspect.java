package mello.cbrcalc.aop;

import lombok.extern.slf4j.Slf4j;
import mello.cbrcalc.entity.LoggingEvent;
import mello.cbrcalc.entity.LoggingType;
import mello.cbrcalc.service.LoggingService;
import mello.cbrcalc.web.ExchangeTransaction;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Aspect
@Slf4j
public class LoggingAspect {
    @Autowired
    LoggingService loggingService;

    @Pointcut("@annotation(LogExecutionMethod)")
    public void logExecutionMethod() {
    }

    @Pointcut("execution(* mello.cbrcalc.service.*.update*DB())")
    public void updateDbPointcut() {
    }

    @After("updateDbPointcut()")
    public void afterDbUpdate(JoinPoint jp) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        MethodSignature methodSignature = (MethodSignature) jp.getSignature();
        String event = String.format("Method: %s", methodSignature.getName());
        LoggingEvent loggingEvent = new LoggingEvent(LocalDateTime.now(), LoggingType.OTHER, userName, event);
        loggingService.saveLoggingEvent(loggingEvent);
    }

    @After("execution(* exchangeCurrency(..))")
    public void afterExchangeAdvice(JoinPoint jp) {
        String event = null;
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        Object[] arguments = jp.getArgs();
        for (Object o : arguments) {
            if (o instanceof ExchangeTransaction) {
                ExchangeTransaction et = (ExchangeTransaction) o;
                event = String.format("From: %s, to: %s, amount from: %s, amount to: %s",
                        et.getCurrencyFrom(), et.getCurrencyTo(), et.getAmountFrom(), et.getAmountTo());
            }
        }

        LoggingEvent loggingEvent = new LoggingEvent(LocalDateTime.now(), LoggingType.EXCHANGECURRENCY, userName, event);
        loggingService.saveLoggingEvent(loggingEvent);
    }
}


