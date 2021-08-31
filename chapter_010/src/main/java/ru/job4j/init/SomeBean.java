package ru.job4j.init;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Компонент, имеющий lifecycle-методы.
 */
@Component
@Scope("singleton")
public class SomeBean {

    private int value;

    @PostConstruct
    public void doInitMethod() {
        System.out.println("Doing initialization...");
    }

    @PreDestroy
    public void doDestroyMethod() {
        System.out.println("Doing destruction....");
    }

    public SomeBean() {
        this.value =  (int) (Math.random() * 100);
    }

    public int getValue() {
        return value;
    }
}