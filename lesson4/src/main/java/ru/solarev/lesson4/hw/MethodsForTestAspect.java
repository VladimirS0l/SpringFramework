package ru.solarev.lesson4.hw;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MethodsForTestAspect {

    @Timer
    @SneakyThrows
    public void timerTestMethod() {
        for (int i = 0; i < 5; i++) {
            System.out.println("It = " + i);
            Thread.sleep(500);
        }
    }

    @RecoverException(noRecoverFor = {NullPointerException.class})
    @SneakyThrows
    public void exceptionTestAnnotation(Integer value) {
        if (value == null) {
            throw new NullPointerException("Value is empty");
        }
        if (value == 0) {
            throw new ArithmeticException("Value should be more zero");
        }
        System.out.println("Ex test annotation result: " + (value * value));
    }

}
