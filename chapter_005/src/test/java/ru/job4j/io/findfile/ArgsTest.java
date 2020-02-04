package ru.job4j.io.findfile;

import org.apache.commons.cli.ParseException;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;

/**
 * Класс содержит тесты класса, выполняющего разбор аргументов командной строки.
 */
public class ArgsTest {
    /**
     * Читающийся в тестах байтовый поток вывода.
     */
    private OutputStream byteout = new ByteArrayOutputStream();

    /**
     * Сохранение стандартного потока вывода.
     */
    private PrintStream stdout = System.out;

    /**
     * Системный разделитель строк.
     */
    private String line = System.lineSeparator();

    /**
     * Подмена потока в стандартном потоке вывода,
     * для того, чтобы мы могли его читать.
     */
    public void loadByteOut() {
        System.setOut(new PrintStream(this.byteout));
    }

    /**
     * Возврат стандартного потока в поток вывода.
     */
    public void loadStandartOut() {
        System.setOut(this.stdout);
    }


    /**
     * Здесь проверяется, что метод проверки достаточности аргументов возвращает истину,
     * когда заданных аргументов достаточно для работы программы.
     *
     * @throws ParseException бросается, если возникнут проблемы с разбором аргументов командной строки.
     */
    @Test
    public void whenValidOptionsThenAlwaysHasAllOptions() throws ParseException {
        String[] args;
        Boolean result;
        Boolean expected = true;
        args = new String[]{"-d", "directory", "-n", "filename", "-f", "patternisfile", "-o", "outfile"};
        Args argParserWithFile1 = new Args(args);
        result = argParserWithFile1.hasAllOption();
        Assert.assertThat(result, is(expected));
        args = new String[]{"-d", "directory", "-n", "filename", "-m", "filemask", "-o", "outfile"};
        Args argParserWithFile2 = new Args(args);
        result = argParserWithFile2.hasAllOption();
        Assert.assertThat(result, is(expected));
        args = new String[]{"-d", "directory", "-n", "filename", "-r", "regexp", "-o", "outfile"};
        Args argParserWithFile3 = new Args(args);
        result = argParserWithFile3.hasAllOption();
        Assert.assertThat(result, is(expected));
    }

    /**
     * Здесь проверяется, что при нехватке значения одного из аргументов командной строки
     * будет получено исключение парсинга.
     *
     * @throws ParseException бросается, если возникнут проблемы с разбором аргументов командной строки.
     */
    @Test
            (expected = ParseException.class)
    public void whenNoOptionValueThenMissingArgumentException() throws ParseException {
        String[] args;
        args = new String[]{"-d", "directory", "-n", "filename", "-f", "patternisfile", "-o"};
        Args argParserWithFile = new Args(args);
    }

    /**
     * Здесь проверяется, что метод проверки достаточности аргументов возвращает ложь,
     * когда заданных аргументов недостаточно для работы программы.
     *
     * @throws ParseException бросается, если возникнут проблемы с разбором аргументов командной строки.
     */
    @Test
    public void whenNoOptionThenHasAllOptionsIsFalse() throws ParseException {
        String[] args;
        args = new String[]{"-d", "directory", "-n", "filename", "-f", "patternisfile"};
        Args argParserWithFile = new Args(args);
        Boolean result = argParserWithFile.hasAllOption();
        Boolean expected = false;
        Assert.assertThat(result, is(expected));
    }

    /**
     * Здесь проверяется, что при указании непредусмотренной опции
     * будет получено исключение парсинга.
     *
     * @throws ParseException бросается, если возникнут проблемы с разбором аргументов командной строки.
     */
    @Test
            (expected = ParseException.class)
    public void whenOtherOptionsThenUnrecognizedOptionException() throws ParseException {
        String[] args;
        args = new String[]{"-d", "directory", "-n", "filename", "-f", "patternisfile", "-z", "other"};
        Args argParserWithFile = new Args(args);
    }

    /**
     * Здесь проверяется, что правильно определяется заданный паттерн
     * поиска файлов в случае, если паттерн поиска - поиск по имени файла.
     *
     * @throws ParseException бросается, если возникнут проблемы с разбором аргументов командной строки.
     */
    @Test
    public void whenSetFilePatternThenGotRightValue() throws ParseException {
        String[] args = new String[]{"-d", "directory", "-n", "filename", "-f", "patternisfile", "-o", "outfile"};
        Args argParser = new Args(args);
        String result = argParser.getPattern();
        String expected = "file";
        Assert.assertThat(result, is(expected));
    }

    /**
     * Здесь проверяется, что правильно определяется заданный паттерн
     * поиска файлов в случае, если паттерн поиска - поиск по маске.
     *
     * @throws ParseException бросается, если возникнут проблемы с разбором аргументов командной строки.
     */
    @Test
    public void whenSetMaskPatternThenGotRightValue() throws ParseException {
        String[] args = new String[]{"-d", "directory", "-n", "filename", "-m", "mask", "-o", "outfile"};
        Args argParser = new Args(args);
        String result = argParser.getPattern();
        String expected = "mask";
        Assert.assertThat(result, is(expected));
    }

    /**
     * Здесь проверяется, что правильно определяется заданный паттерн
     * поиска файлов в случае, если паттерн поиска - поиск по регулярному выражению.
     *
     * @throws ParseException бросается, если возникнут проблемы с разбором аргументов командной строки.
     */
    @Test
    public void whenSetRegexpPatternThenGotRightValue() throws ParseException {
        String[] args = new String[]{"-d", "directory", "-n", "filename", "-r", "regexp", "-o", "outfile"};
        Args argParser = new Args(args);
        String result = argParser.getPattern();
        String expected = "regexp";
        Assert.assertThat(result, is(expected));
    }

    /**
     * Здесь проверяется, что правильно распечатывается подсказка.
     *
     * @throws ParseException бросается, если возникнут проблемы с разбором аргументов командной строки.
     */
    @Test
    public void whenPrintHelpThenHelpPrints() throws ParseException {
        this.loadByteOut();
        String expected = new StringBuilder()
                .append("usage: java -jar archive.jar [-d] [-n] [-m|-f|-r] [-o]").append(line)
                .append(" -d,--directory <arg>   Directory in which must start searching.").append(line)
                .append(" -f,--fullname          Use full file name in searching.").append(line)
                .append(" -m,--mask              Use mask for file name in searching.").append(line)
                .append(" -n,--name <arg>        File name that being search.").append(line)
                .append(" -o,--output <arg>      Output log file.").append(line)
                .append(" -r,--regexp            Use regexp for file name in searching.").append(line)
                .toString();
        String[] args = new String[]{"-d", "directory", "-n", "filename", "-f", "patternisfile", "-o", "outfile"};
        Args argParserWithFile = new Args(args);
        argParserWithFile.printHelp();
        String result = this.byteout.toString();
        this.loadStandartOut();
        Assert.assertThat(result, is(expected));
    }
}