package ru.solarev.lesson4.hw;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

@Aspect
@Component
@Slf4j
public class AspectMethods {

    @Pointcut("@annotation(Timer)")
    public void timerMethod() {};

    @Around("timerMethod()")
    @SneakyThrows
    public void aroundTimerMethod(ProceedingJoinPoint proceedingJoinPoint ) {
        long start = System.nanoTime();
        proceedingJoinPoint.proceed();
        Class aClass = proceedingJoinPoint.getSignature().getDeclaringType();
        String method = proceedingJoinPoint.getSignature().getName();
        long finish = System.nanoTime();
        log.info("Class = {}, method = {}: {} ms" , aClass, method, (finish - start) / 1_000_000);
    }

    @Pointcut("@annotation(RecoverException)")
    public void exceptionMethod() {};


    @Around("exceptionMethod()")
    @SneakyThrows
    public void aroundExceptionMethod(ProceedingJoinPoint proceedingJoinPoint) {
        Class<? extends RuntimeException>[] exeptions = extractNoRecoveryFor(proceedingJoinPoint);
        try {
            proceedingJoinPoint.proceed();
        } catch (RuntimeException runtimeException) {
            for (int i = 0; i < exeptions.length; i++) {
                if (exeptions[i] == runtimeException.getClass()) {
                    throw runtimeException;
                }
            }
            log.error("Catch exception: {}, class = {}", runtimeException.getMessage(), runtimeException.getClass());
        }
    }

    private Class<? extends RuntimeException>[] extractNoRecoveryFor(ProceedingJoinPoint joinPoint) {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    RecoverException annotation = signature.getMethod().getAnnotation(RecoverException.class);
    if (annotation != null) {
      return annotation.noRecoverFor();
    }
    return joinPoint.getTarget().getClass().getAnnotation(RecoverException.class).noRecoverFor();
  }


}
