package ru.job4j.io.findfile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Класс содержит методы для поиска файлов по критерию.
 */
public class FindFile {

    /**
     * Основной метод, выполняющий поиск файлов по критерию.
     *
     * @param path      путь, по которому будет выполняться поиск файлов
     * @param predicate предикат для поиска файлов.
     * @return список с результатами поиска.
     */
    public LinkedList<File> process(String path, Predicate<File> predicate) {
        LinkedList<File> result = new LinkedList<>();
        Queue<File> queue = new LinkedList<>();
        File root = new File(path);
        queue.offer(root);
        while (!queue.isEmpty()) {
            File nextFileEntry = queue.poll();
            if (nextFileEntry.isDirectory()) {
                for (File file : nextFileEntry.listFiles()) {
                    queue.offer(file);
                }
            } else {
                if (predicate.test(nextFileEntry)) {
                    result.add(nextFileEntry);
                }
            }
        }
        return result;
    }

    /**
     * Вспомогательный метод: сбросить в файл результатов пути к найденным файлам.
     *
     * @param files  список найденных файлов.
     * @param output файл для вывода результатов.
     */
    public void flush(LinkedList<File> files, String output) {
        try (PrintWriter out = new PrintWriter(new FileWriter(output))) {
            for (File file : files) {
                out.println(file.getAbsolutePath());
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Получить предикат для поиска файлов на основе входных параметров.
     *
     * @param pattern      тип шаблона имени.
     * @param expectedName расшифровка шаблона имени
     * @return соответствующий предикат.
     */
    public Predicate<File> getPredicate(String pattern, final String expectedName) {
        Predicate<File> predicate = null;
        if (pattern.equals("file")) {
            predicate = file -> expectedName.equals(file.getName());
        } else if (pattern.equals("regexp")) {
            predicate = file -> {
                Pattern regexp = Pattern.compile(expectedName, Pattern.CASE_INSENSITIVE);
                return regexp.matcher(file.getName()).matches();
            };
        } else if (pattern.equals("mask")) {
            predicate = file -> {
                String rdot = ".",
                        dot = "\\.",
                        scdot = "\\\\.",
                        star = "\\*",
                        rstar = ".*",
                        q = "\\?",
                        cor = "|", or = "\\;";
                String mask = expectedName;
                mask = mask
                        .replaceAll(dot, scdot).replaceAll(star, rstar)
                        .replaceAll(q, rdot).replaceAll(or, cor);
                Pattern regexp = Pattern.compile(mask, Pattern.CASE_INSENSITIVE);
                return regexp.matcher(file.getName()).matches();
            };
        }

        return predicate;
    }
}