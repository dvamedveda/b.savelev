package ru.job4j.io.findfile;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.LinkedList;
import java.util.function.Predicate;

import static org.hamcrest.CoreMatchers.is;

/**
 * Класс содержит тесты класса для поиска файлов.
 */
public class FindFileTest {

    /**
     * Тестовая директория для создания тестовых файлов.
     * Удаляется при завершении тестирования.
     */
    @Rule
    public TemporaryFolder root = new TemporaryFolder();

    /**
     * Системные переменные разделителя пути и новой строки.
     */
    private String slash = System.getProperty("file.separator");
    private String line = System.getProperty("line.separator");

    /**
     * Здесь проверяется поиск файлов, если в качестве шаблона поиска задано имя файла.
     *
     * @throws IOException бросается, если возникнут проблемы с вводом-выводом.
     */
    @Test
    public void whenProcessFilesByNameThenProcessSuccess() throws IOException {
        String rootPath = root.getRoot().getPath();
        String firstFile = "first.file";
        String secondFile = "second.file";
        root.newFile(firstFile);
        root.newFile(secondFile);
        File directory = root.newFolder("directory");
        File thirdFile = new File(directory.getPath() + slash + "third.txt");
        thirdFile.createNewFile();
        File resultFile = root.newFile("result");
        FindFile finder = new FindFile();
        Predicate<File> predicate = finder.getPredicate("file", "first.file");
        LinkedList<File> resultList = finder.process(rootPath, predicate);
        finder.flush(resultList, resultFile.getPath());
        String result = this.readResult(resultFile.getPath());
        String expected = new StringBuilder().append(rootPath).append(slash).append(firstFile).append(line).toString();
        Assert.assertThat(result, is(expected));
    }

    /**
     * Здесь проверяется поиск файлов, если в качестве шаблона поиска задана маска имен файлов.
     *
     * @throws IOException бросается, если возникнут проблемы с вводом-выводом.
     */
    @Test
    public void whenProcessFilesByMaskThenProcessSuccess() throws IOException {
        String rootPath = root.getRoot().getPath();
        String firstFile = "first.file";
        String secondFile = "second.file";
        root.newFile(firstFile);
        root.newFile(secondFile);
        File directory = root.newFolder("directory");
        File thirdFile = new File(directory.getPath() + slash + "third.txt");
        thirdFile.createNewFile();
        File resultFile = root.newFile("result");
        FindFile finder = new FindFile();
        Predicate<File> predicate = finder.getPredicate("mask", "*.?ile");
        LinkedList<File> resultList = finder.process(rootPath, predicate);
        finder.flush(resultList, resultFile.getPath());
        String result = this.readResult(resultFile.getPath());
        String expected = new StringBuilder().
                append(rootPath).append(slash).append(firstFile).append(line).
                append(rootPath).append(slash).append(secondFile).append(line).toString();
        Assert.assertThat(result, is(expected));
    }

    /**
     * Здесь проверяется поиск файлов, если в качестве шаблона поиска задано регулярное выражение.
     *
     * @throws IOException бросается, если возникнут проблемы с вводом-выводом.
     */
    @Test
    public void whenProcessFilesByRegexpThenProcessSuccess() throws IOException {
        String rootPath = root.getRoot().getPath();
        String firstFile = "first.file";
        String secondFile = "second.file";
        root.newFile(firstFile);
        root.newFile(secondFile);
        File directory = root.newFolder("directory");
        File thirdFile = new File(directory.getPath() + slash + "third.txt");
        thirdFile.createNewFile();
        File resultFile = root.newFile("result");
        FindFile finder = new FindFile();
        Predicate<File> predicate = finder.getPredicate("regexp", ".*d\\..*");
        LinkedList<File> resultList = finder.process(rootPath, predicate);
        finder.flush(resultList, resultFile.getPath());
        String result = this.readResult(resultFile.getPath());
        String expected = new StringBuilder().
                append(rootPath).append(slash).append(secondFile).append(line).
                append(rootPath).append(slash).append(directory.getName()).append(slash).append(thirdFile.getName()).append(line).
                toString();
        Assert.assertThat(result, is(expected));
    }

    /**
     * Вспомогательный метод для чтения файла результатов.
     *
     * @param path путь к файлу результатов.
     * @return строка с содержимым файла результатов.
     * @throws IOException бросается, если возникнут проблемы с вводом-выводом.
     */
    private String readResult(String path) throws IOException {
        StringBuilder result = new StringBuilder();
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream(path))) {
            while (reader.ready()) {
                result.append((char) reader.read());
            }
        }
        return result.toString();
    }
}