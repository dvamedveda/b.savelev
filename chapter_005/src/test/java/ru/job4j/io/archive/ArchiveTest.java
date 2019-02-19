package ru.job4j.io.archive;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Тесты класса ru.job4j.io.archive.Archive.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class ArchiveTest {
    private String slash = System.getProperty("file.separator");
    private File root = new File(System.getProperty("java.io.tmpdir") + slash + "archive");
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
            root.mkdirs();
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
     * Проверяем что при поиске файлов по указанному пути, список файлов фильтруется.
     */
    @Test
    public void whenSearchFilesThenResultIsCorrect() {
        Archive archive = new Archive();
        String[] exts = new String[]{".sixth", ".fifth", ".fourth"};
        List<File> expected = new ArrayList<>();
        expected.add(first);
        expected.add(second);
        expected.add(third);
        expected.add(firstDir);
        expected.add(secondDir);
        expected.add(thirdDir);
        List<File> result = archive.filterFiles(root.getPath(), exts);
        Assert.assertTrue(result.size() == expected.size());
        Assert.assertTrue(result.containsAll(expected));
    }

    /**
     * Проверяем что при работе метода по архивированию, создается файл архива.
     */
    @Test
    public void whenArchiveFilesThenArchiveCreates() throws IOException {
        Archive archive = new Archive();
        String[] exts = new String[]{".sixth", ".fifth", ".fourth"};
        File output = new File(root + slash + "test_archive.zip");
        List<File> files = archive.filterFiles(root.getPath(), exts);
        archive.writeArchive(files, output.getAbsolutePath());
        Assert.assertTrue(output.exists());
        output.delete();
    }

    /**
     * Проверяем что при работе метода по архивированию, исходные файлы не исчезают.
     */
    @Test
    public void whenArchiveCreateThenFilesNotMoved() throws IOException {
        Archive archive = new Archive();
        String[] exts = new String[]{".sixth", ".fifth", ".fourth"};
        File output = new File(root + slash + "test_archive.zip");
        List<File> files = archive.filterFiles(root.getPath(), exts);
        archive.writeArchive(files, output.getAbsolutePath());
        List<File> paths = new ArrayList<>();
        paths.add(firstDir);
        paths.add(secondDir);
        paths.add(thirdDir);
        paths.add(first);
        paths.add(second);
        paths.add(third);
        paths.add(fourth);
        paths.add(fifth);
        paths.add(sixth);
        for (File path : paths) {
            Assert.assertTrue(path.exists());
        }
        output.delete();
    }
}