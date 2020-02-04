package ru.job4j.io.findfile;

import org.apache.commons.cli.ParseException;

import java.io.File;
import java.util.LinkedList;
import java.util.function.Predicate;

/**
 * Главный класс, служащий для запуска программы.
 */
public class Start {

    /**
     * Парсим аргументы командной строки и с помощью них ищем файлы.
     * Если аргументов командной строки недостаточно, то прекращаем работу программы.
     *
     * @param args аргументы командной строки.
     */
    public static void main(String[] args) {
        try {
            Args options = new Args(args);
            if (!options.hasAllOption()) {
                options.printHelp();
            } else {
                FindFile finder = new FindFile();
                Predicate<File> predicate = finder.getPredicate(options.getPattern(), options.getName());
                LinkedList<File> files = finder.process(options.getDirectory(), predicate);
                finder.flush(files, options.getOutput());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}