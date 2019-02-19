package ru.job4j.io.archive;

import org.junit.Assert;
import org.junit.Test;

/**
 * Тесты класса ru.job4j.io.archive.Args.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class ArgsTest {

    /**
     * Тут проверяется корректный парсинг опций.
     */
    @Test
    public void whenParamsAddsThenAllExistsAsOption() {
        Args args = new Args(new String[]{"-d", "F:\\EVENTS1", "-o", "F:\\MyArchive.zip", "-e", ".cvr", ".props", ".dll"});
        Assert.assertTrue(args.getDirectory().equals("F:\\EVENTS1"));
        Assert.assertTrue(args.getOutput().equals("F:\\MyArchive.zip"));
        Assert.assertEquals(args.getExcludes(), new String[]{".cvr", ".props", ".dll"});
    }

    /**
     * Тут проверяется проверка опций на null, если опции не пусты.
     */
    @Test
    public void whenAllParamsExistsThenAllParamsIsTrue() {
        Args args = new Args(new String[]{"-d", "F:\\EVENTS1", "-o", "F:\\MyArchive.zip", "-e", ".cvr", ".props", ".dll"});
        Assert.assertTrue(args.hasAllOption());
    }

    /**
     * Тут проверяется проверка опций на null, если опции пусты.
     */
    @Test
    public void whenParamMissingThenAllParamsIsFalse() {
        Args args = new Args(new String[]{"-d", "F:\\EVENTS1", "-o", "F:\\MyArchive.zip"});
        Assert.assertFalse(args.hasAllOption());
    }
}