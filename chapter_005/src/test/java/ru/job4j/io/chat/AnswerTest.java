package ru.job4j.io.chat;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Тесты класса ru.job4j.io.chat.
 */
public class AnswerTest {
    /**
     * Временная директория для хранения файла с ответами чата.
     */
    private String tmpdir = System.getProperty("java.io.tmpdir");

    /**
     * Системный разделитель пути в файлах.
     */
    private String slash = System.getProperty("file.separator");

    /**
     * Файл ответов, использующийся в тестах.
     */
    private File answers = new File(tmpdir + slash + "answers.txt");

    /**
     * Создание файла ответов перед запуском тестов.
     *
     * @throws IOException бросается при возникновении исключений при создании файла с ответами.
     */
    @Before
    public void createAnswers() throws IOException {
        this.answers.createNewFile();
        String text = "Привет!\n"
                + "Не знаю!\n"
                + "Да.\n"
                + "Нет.\n"
                + "Конечно!\n"
                + "Не может быть!\n"
                + "Все так и есть.\n"
                + "До свидания!";
        FileWriter fileWriter = new FileWriter(answers);
        fileWriter.write(text);
        fileWriter.close();
    }

    /**
     * Удаление файла отвтеов после запуска тестов.
     */
    @After
    public void deleteAnswers() {
        this.answers.delete();
    }

    /**
     * Проверка возможности получить один из ответов.
     */
    @Test
    public void whenCreateChatThenCanGetAnswers() {
        Answer chat = new Answer(this.answers.getPath());
        String result = chat.getAnswer();
        List<String> answers = chat.getAnswersList();
        Assert.assertTrue(result, answers.contains(result));
    }
}