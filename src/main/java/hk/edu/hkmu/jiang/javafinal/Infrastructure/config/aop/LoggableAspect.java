package hk.edu.hkmu.jiang.javafinal.Infrastructure.config.aop;

import hk.edu.hkmu.jiang.javafinal.common.annotation.Loggable;
import hk.edu.hkmu.jiang.javafinal.common.util.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggableAspect {

    @Around("@annotation(loggable)")
    public Object logMethod(ProceedingJoinPoint joinPoint, Loggable loggable) throws Throwable {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();

        Object result = joinPoint.proceed();
        if (loggable.logParameters()) {
            log.info("Method {}.{} called with args: {}",
                    className, methodName, GsonUtil.getGson().toJson(joinPoint.getArgs()));
        }
        if (loggable.logResult()) {
            log.info("Method {}.{} returned: {}", className, methodName, GsonUtil.getGson().toJson(result));
        }

        return result;
    }
}