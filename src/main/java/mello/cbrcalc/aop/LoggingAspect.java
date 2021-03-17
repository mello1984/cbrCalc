package mello.cbrcalc.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Pointcut("@annotation(LogExecutionMethod)")
    public void logExecutionMethod() {
    }

    @Before("logExecutionMethod()")
    public void beforeExecutionMethod() {
        System.out.println(">>>>>>>>>>>>>>>>>>> beforeExecutionMethod >>>>>>>>>>>>>>>>>>>");
        log.warn(">>>>>>>>>>>>>>>>>>> beforeExecutionMethod >>>>>>>>>>>>>>>>>>>");
    }

    @Before("execution(* mello.cbrcalc.controller.BaseController.exchangeCurrency(..))")
    public void beforeExchangeAdvice(JoinPoint point) {
        System.out.println(">>>>>>>>>>>>>>>>>>> beforeExchangeAdvice start >>>>>>>>>>>>>>>>>>>");
        log.warn(">>>>>>>>>>>>>>>>>>> beforeExchangeAdvice start >>>>>>>>>>>>>>>>>>>");
    }

    @Before("execution(public String login())")
    public void userLoginAdvice(JoinPoint point) {
        System.out.println(">>>>>>>>>>>>>>>>>>> before Login start >>>>>>>>>>>>>>>>>>>");
        log.warn(">>>>>>>>>>>>>>>>>>> before Login start >>>>>>>>>>>>>>>>>>>");
    }

    @Before("execution(public String valCodePage(..))")
    public void valCodePageAdvice(JoinPoint point) {
        System.out.println(">>>>>>>>>>>>>>>>>>> before Login start >>>>>>>>>>>>>>>>>>>");
        log.warn(">>>>>>>>>>>>>>>>>>> before Login start >>>>>>>>>>>>>>>>>>>");
    }
}

