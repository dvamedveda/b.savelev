package ru.job4j.io.search;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс содержит тесты класса Search.
 */
public class SearchTest {
    private File root = new File(System.getProperty("java.io.tmpdir"));
    private String slash = System.getProperty("file.separator");
    private File first = new File(root.getAbsolutePath() + slash + "first.first");
    private File firstDir = new File(root.getAbsolutePath() + slash + "firstDirectory");
    private File second = new File(firstDir.getAbsolutePath() + slash + "second.second");
    private File secondDir = new File(firstDir.getAbsolutePath() + slash + "secondDirectory");
    private File third = new File(secondDir.getAbsolutePath() + slash + "third.third");
    private File fourth = new File(secondDir.getAbsolutePath() + slash + "fourth.fourth");
    private File thirdDir = new File(root.getAbsolutePath() + slash + "thirdDirectory");
    private File fifth = new File(thirdDir.getAbsolutePath() + slash + "fifth.fifth");
    private File sixth = new File(firstDir.getAbsolutePath() + slash + "sixth.sixth");

    /**
     * Подготовка тестового окружения.
     */
    @Before
    public void prepareDirectories() {
        System.out.println(this.root);
        try {
            first.createNewFile();
            firstDir.mkdir();
            second.createNewFile();
            secondDir.mkdir();
            third.createNewFile();
            fourth.createNewFile();
            thirdDir.mkdir();
            fifth.createNewFile();
            sixth.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Удаление тестового окружения.
     */
    @After
    public void deleteDirectories() {
        first.delete();
        second.delete();
        third.delete();
        fourth.delete();
        fifth.delete();
        sixth.delete();
        firstDir.delete();
        secondDir.delete();
        thirdDir.delete();
    }

    /**
     * Здесь проверяется, что при наличии файлов с искомыми расширениями,
     * возвращается правильный список.
     */
    @Test
    public void whenSearchExistsFilesThenGetRightList() {
        String path = System.getProperty("java.io.tmpdir");
        List<String> exts = new ArrayList<>();
        exts.add(".first");
        exts.add(".second");
        exts.add(".third");
        exts.add(".fourth");
        exts.add(".fifth");
        exts.add(".sixth");
        List<File> expected = new LinkedList<>();
        expected.add(first);
        expected.add(second);
        expected.add(third);
        expected.add(fourth);
        expected.add(fifth);
        expected.add(sixth);
        Search search = new Search();
        List<File> result = search.files(path, exts);
        Assert.assertTrue(result.size() == expected.size());
        Assert.assertTrue(result.containsAll(expected));
    }

    /**
     * Здесь проверяется, что при отсутствии файлов с искомыми расширениями,
     * возвращается пустой список.
     */
    @Test
    public void whenSearchNotExistsFilesThenGetEmptyList() {
        String path = System.getProperty("java.io.tmpdir");
        List<String> exts = new ArrayList<>();
        exts.add(".seventh");
        exts.add(".ninth");
        Search search = new Search();
        List<File> result = search.files(path, exts);
        Assert.assertTrue(result.size() == 0);
        Assert.assertTrue(result.isEmpty());
    }
}