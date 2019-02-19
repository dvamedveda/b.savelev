package ru.job4j.io.archive;

import org.apache.commons.cli.*;

/**
 * Класс просто позволяет удобно работать с опциями из командной строки.
 */
public class Args {
    /**
     * Объект описывающий список опций.
     */
    private Options options = new Options();

    /**
     * Парсер опций.
     */
    private CommandLineParser parser = new DefaultParser();

    /**
     * Готовые к употреблению опции.
     */
    private CommandLine cmd = null;

    /**
     * Вспомогательный класс для распечатывания хелпа.
     */
    HelpFormatter formatter = new HelpFormatter();

    /**
     * Объявляем список опций.
     * Пробрасываем полученные в psvm массив аргументов командной строки.
     * И распарсиваем их.
     *
     * @param args
     */
    public Args(String[] args) {
        options.addOption("d", "directory", true, "Directory that must be archived.");
        options.addOption("o", "output", true, "Output archive file.");

        Option excludeOption = new Option("e", "exclude", true, "Excluded extensions.");
        excludeOption.setArgs(Option.UNLIMITED_VALUES);
        excludeOption.setValueSeparator(' ');
        options.addOption(excludeOption);

        try {
            this.cmd = parser.parse(options, args);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Получить опцию директории.
     *
     * @return значение опции директории.
     */
    public String getDirectory() {
        return this.cmd.getOptionValue("d");
    }

    /**
     * Получить опцию вывода.
     *
     * @return значение опции вывода.
     */
    public String getOutput() {
        return this.cmd.getOptionValue("o");
    }

    /**
     * Получить опцию исключений.
     *
     * @return значение опции исключений.
     */
    public String[] getExcludes() {
        return this.cmd.getOptionValues("e");
    }

    /**
     * Распечатать хелп.
     */
    public void printHelp() {
        formatter.printHelp("java -jar archive.jar [-d] [-e] [-o]", options);
    }

    /**
     * Метод для проверки - все ли опции переданы при запуске программы.
     *
     * @return результат проверки значений опций на Null.
     */
    public boolean hasAllOption() {
        return this.getExcludes() != null && this.getOutput() != null && this.getDirectory() != null;
    }
}