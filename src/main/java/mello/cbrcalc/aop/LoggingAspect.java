package mello.cbrcalc.aop;

import lombok.extern.slf4j.Slf4j;
import mello.cbrcalc.entity.LoggingEvent;
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
        String event = String.format("User: %s, method: %s", userName, methodSignature.getName());
        loggingService.saveLoggingEvent(new LoggingEvent(LocalDateTime.now(), event));
    }

    @After("execution(* exchangeCurrency(..))")
    public void beforeExchangeAdvice(JoinPoint jp) {
        Object[] arguments = jp.getArgs();
        String etData = null;
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        for (Object o : arguments) {
            if (o instanceof ExchangeTransaction) {
                ExchangeTransaction et = (ExchangeTransaction) o;
                etData = String.format("User: %s, from: %s, to: %s, amount from: %s, amount to: %s",
                        userName, et.getCurrencyFrom(), et.getCurrencyTo(), et.getAmountFrom(), et.getAmountTo());
            }
        }
        loggingService.saveLoggingEvent(new LoggingEvent(LocalDateTime.now(), etData));
    }
}

//    @Before("logExecutionMethod()")
//    public void beforeExecutionMethod() {
//        System.out.println(">>>>>>>>>>>>>>>>>>> beforeExecutionMethod >>>>>>>>>>>>>>>>>>>");
//        log.warn(">>>>>>>>>>>>>>>>>>> beforeExecutionMethod >>>>>>>>>>>>>>>>>>>");
//    }
//


