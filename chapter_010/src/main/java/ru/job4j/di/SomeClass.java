package ru.job4j.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Класс, демонстрирующий вызов lifecycle-методов бинов при управлении их контекстом.
 */
public class SomeClass {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("ru.job4j.di");
        context.refresh();
        System.out.println(context.getBean(SomeBean.class).getValue());
        System.out.println(context.getBean(SomeBean.class).getValue());
        System.out.println(context.getBean(SomeBean.class).getValue());
        System.out.println(context.getBean(SomeBean.class).getValue());
        System.out.println(context.getBean(SomeBean.class).getValue());
        System.out.println(context.getBean(SomeBean.class).getValue());
        context.close();
    }
}