package ru.job4j.template;

        import java.util.Map;

/**
 * Интерфейс генератора строк.
 */
public interface Generator {

    /**
     * Сгенерировать строку на основе переданного шаблона и набора параметров.
     *
     * @param template строка-шаблон.
     * @param args     список параметров.
     * @return готовая строка.
     */
    String produce(String template, Map<String, String> args);
}