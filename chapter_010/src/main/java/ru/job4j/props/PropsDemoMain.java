package ru.job4j.props;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Демонстрация загрузки свойств из файлов properties.
 */
public class PropsDemoMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("ru.job4j.props");
        context.refresh();
        PropsDemoBean bean = context.getBean(PropsDemoBean.class);
        System.out.println(bean.getInternalStringValue());
        System.out.println(bean.getInternalIntValue());
        System.out.println("---------------");
        System.out.println(bean.getExternalStringValue());
        System.out.println(bean.getExternalIntValue());
    }
}