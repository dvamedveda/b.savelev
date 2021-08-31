package ru.job4j.props;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Бин, обращающийся к properties-файлам.
 * Находящимся в classpath и внешним.
 */
@Component
@PropertySource("classpath:app.properties")
//@PropertySource("file:f:/my.properties")
public class PropsDemoBean {

    @Value("demo.internal.string")
    private String internalStringValue;

    @Value("${demo.internal.int}")
    private int internalIntValue;

//    @Value("demo.external.string")
    private String externalStringValue;

//    @Value("${demo.external.int}")
    private int externalIntValue;

    public String getInternalStringValue() {
        return internalStringValue;
    }

    public int getInternalIntValue() {
        return internalIntValue;
    }

    public String getExternalStringValue() {
        return externalStringValue;
    }

    public int getExternalIntValue() {
        return externalIntValue;
    }
}