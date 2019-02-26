package ru.job4j.io.chat;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;

/**
 * Тесты класса ru.job4j.io.chat.
 */
public class LogicTest {
    /**
     * Временная директория для хранения файла с ответами чата.
     */
    private final String tmpdir = System.getProperty("java.io.tmpdir");

    /**
     * Системный разделитель пути в файлах.
     */
    private final String slash = System.getProperty("file.separator");

    /**
     * Системный символ переноса строки.
     */
    private final String separator = System.lineSeparator();

    /**
     * Файл ответов, использующийся в тестах.
     */
    private final File answers = new File(tmpdir + slash + "answers.txt");

    /**
     * Поток вывода, используеющийся в тестах.
     */
    final ByteArrayOutputStream byteout = new ByteArrayOutputStream();

    /**
     * Создание файла ответов перед запуском тестов.
     *
     * @throws IOException бросается при возникновении исключений при создании файла с ответами.
     */
    @Before
    public void createAnswers() throws IOException {
        answers.createNewFile();
        String text = "hello";
        FileWriter fileWriter = new FileWriter(answers);
        fileWriter.write(text);
        fileWriter.close();
    }

    /**
     * Удаление файла отвтеов после запуска тестов.
     */
    @After
    public void deleteAnsers() {
        answers.delete();
    }

    /**
     * Проверка успешного завершения чата после ввода кодового слова "finish".
     *
     * @throws IOException бросается в случае возникновения проблем с потоками ввода и вывода.
     */
    @Test
    public void whenUserWriteFinishThenChatFinishes() throws IOException {
        String text = new StringBuilder("finish").append(separator)
                .toString();
        ByteArrayInputStream bin = new ByteArrayInputStream(text.getBytes());
        Logic logic = new Logic(answers.getPath(), bin, this.byteout);
        logic.start();
        String result = new String(this.byteout.toByteArray());
        String expected = new StringBuilder("Hello!").append(separator)
                .append("Finished").append(separator)
                .toString();
        Assert.assertThat(result, is(expected));
    }

    /**
     * Проверка успешного завершения вывода чата после ввода кодового слова "stop".
     *
     * @throws IOException бросается в случае возникновения проблем с потоками ввода и вывода.
     */
    @Test
    public void whenUserStopsChatThenChatStops() throws IOException {
        String text = new StringBuilder("hi!").append(separator)
                .append("stop").append(separator)
                .append("finish").append(separator)
                .toString();
        ByteArrayInputStream bin = new ByteArrayInputStream(text.getBytes());
        Logic logic = new Logic(answers.getPath(), bin, this.byteout);
        logic.start();
        String result = new String(this.byteout.toByteArray());
        String expected = new StringBuilder("Hello!").append(separator)
                .append("hello").append(separator)
                .toString();
        Assert.assertThat(result, is(expected));
    }

    /**
     * Проверка успешного возобновления вывода чата после ввода кодового слова "resume".
     *
     * @throws IOException бросается в случае возникновения проблем с потоками ввода и вывода.
     */
    @Test
    public void whenUserWriteStopAndResumeThenChatStoppedAndResumed() throws IOException {
        String text = new StringBuilder("hi!").append(separator)
                .append("stop").append(separator)
                .append("hi").append(separator)
                .append("resume").append(separator)
                .append("hi").append(separator)
                .append("finish").append(separator)
                .toString();
        ByteArrayInputStream bin = new ByteArrayInputStream(text.getBytes());
        Logic logic = new Logic(answers.getPath(), bin, this.byteout);
        logic.start();
        String result = new String(this.byteout.toByteArray());
        String expected = new StringBuilder("Hello!").append(separator)
                .append("hello").append(separator)
                .append("Resumed").append(separator)
                .append("hello").append(separator)
                .append("Finished").append(separator)
                .toString();
        Assert.assertThat(result, is(expected));
    }
}