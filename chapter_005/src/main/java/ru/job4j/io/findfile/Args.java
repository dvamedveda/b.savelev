package ru.job4j.io.findfile;

import org.apache.commons.cli.*;

/**
 * Класс содержит функционал для разбора аргументов командной строки.
 */
public class Args {

    /**
     * Общий класс опций приложения.
     */
    private Options options = new Options();

    /**
     * Группа взаимоисключающих опций для шаблона поиска файла(ов).
     * Это может быть один из: имя файла, маска, регулярное выражение.
     */
    private OptionGroup pattern = new OptionGroup();

    /**
     * Класс парсера командной строки.
     */
    private CommandLineParser parser = new DefaultParser();

    /**
     * Класс командной строки, содержащий аргументы командной строки.
     * Из него мы в процессе работы получаем необходимую нам информацию об опциях программы.
     */
    private CommandLine cmd = null;

    /**
     * Форматтер для печати подсказки.
     */
    HelpFormatter formatter = new HelpFormatter();

    /**
     * Конструктор класса опций.
     *
     * @param args массив аргументов командной строки.
     */
    public Args(String[] args) throws ParseException {
        options.addOption("d", "directory", true, "Directory in which must start searching.");
        options.addOption("n", "name", true, "File name that being search.");
        options.addOption("o", "output", true, "Output log file.");

        pattern.addOption(new Option("m", "mask", false, "Use mask for file name in searching."));
        pattern.addOption(new Option("f", "fullname", false, "Use full file name in searching."));
        pattern.addOption(new Option("r", "regexp", false, "Use regexp for file name in searching."));

        options.addOptionGroup(pattern);

        try {
            this.cmd = parser.parse(options, args);
        } catch (ParseException e) {
            throw new ParseException(e.getMessage());
        }
    }

    /**
     * Получить директорию для сканирования.
     *
     * @return путь к директории.
     */
    public String getDirectory() {
        return this.cmd.getOptionValue("d");
    }

    /**
     * Получить шаблон поиска.
     * Может содержать маску, регулярное выражение или просто имя файла.
     *
     * @return шаблон поиска.
     */
    public String getName() {
        return this.cmd.getOptionValue("n");
    }

    /**
     * Узнать, задана ли опция поиска файла по маске.
     *
     * @return опция задана или нет.
     */
    public boolean getMask() {
        return this.cmd.hasOption("m");
    }

    /**
     * Узнать, задана ли опция поиска файла по имени.
     *
     * @return опция задана или нет.
     */
    public boolean getFile() {
        return this.cmd.hasOption("f");
    }

    /**
     * Узнать, задана ли опция поиска файла по регулярному выражению.
     *
     * @return опция задана или нет.
     */
    public boolean getRegexp() {
        return this.cmd.hasOption("r");
    }

    /**
     * Получить файл для вывода результатов.
     *
     * @return имя файла результатов.
     */
    public String getOutput() {
        return this.cmd.getOptionValue("o");
    }

    /**
     * Получить способ поиска на основе заданных опций.
     *
     * @return способ поиска: маска, файл или регулярное выражение.
     */
    public String getPattern() {
        String result = "";
        if (this.pattern.getSelected().equals("m")) {
            result = "mask";
        } else if (this.pattern.getSelected().equals("f")) {
            result = "file";
        } else if (this.pattern.getSelected().equals("r")) {
            result = "regexp";
        }
        return result;
    }

    /**
     * Распечатать подсказку по использованию программы.
     */
    public void printHelp() {
        formatter.printHelp("java -jar archive.jar [-d] [-n] [-m|-f|-r] [-o]", options);
    }

    /**
     * Проверка, заданы ли все необходимые для работы программы.
     *
     * @return опций достаточно или нет.
     */
    public boolean hasAllOption() {
        return this.getDirectory() != null
                && this.getOutput() != null
                && this.getName() != null
                && (this.getFile() | this.getMask() | this.getRegexp());
    }
}