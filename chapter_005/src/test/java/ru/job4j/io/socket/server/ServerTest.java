package ru.job4j.io.socket.server;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.Socket;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Класс содержит тесты класса ru.job4j.io.socket.client.Server.
 */
public class ServerTest {
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
    @Before
    public void loadByteOut() {
        System.setOut(new PrintStream(this.byteout));
    }

    /**
     * Возврат стандартного потока в поток вывода.
     */
    @After
    public void loadStandartOut() {
        System.setOut(this.stdout);
    }

    /**
     * Проверка того, что при получении сообщения "exit", сервер завершает свою работу.
     *
     * @throws IOException бросается при возникновении исключений при работе с потоками ввода и вывода.
     */
    @Test
    public void whenExitMessageThenServerExits() throws IOException {
        String inString = new StringBuilder()
                .append("exit").append(line)
                .toString();
        String expectedSocketOut = new StringBuilder("exit").append(line).append(line)
                .toString();
        String expectedStdOut = new StringBuilder("Server: waiting command...").append(line)
                .append("Server got from client: exit").append(line)
                .toString();
        this.testServer(inString, expectedSocketOut, expectedStdOut);
    }

    /**
     * Проверка того, что при получении сообщения кроме "exit", сервер отправляет его снова на клиент,
     * в качестве полезной работы.
     *
     * @throws IOException бросается при возникновении исключений при работе с потоками ввода и вывода.
     */
    @Test
    public void whenReceiveMessageThenRepeatsIt() throws IOException {
        String inString = new StringBuilder()
                .append("hi").append(line)
                .append("exit").append(line)
                .toString();
        String expectedSocketOut = new StringBuilder("hi").append(line).append(line)
                .append("exit").append(line).append(line)
                .toString();
        String expectedStdOut = new StringBuilder("Server: waiting command...").append(line)
                .append("Server got from client: hi").append(line)
                .append("Server: waiting command...").append(line)
                .append("Server got from client: exit").append(line)
                .toString();
        this.testServer(inString, expectedSocketOut, expectedStdOut);
    }

    /**
     * Проверка того, что при получении сообщения "hello", сервер дополнительно отправляет на клиент приветствие.
     *
     * @throws IOException бросается при возникновении исключений при работе с потоками ввода и вывода.
     */
    @Test
    public void whenReceiveHelloThenGreetingsAndRepeatHello() throws IOException {
        String inString = new StringBuilder()
                .append("hello").append(line)
                .append("exit").append(line)
                .toString();
        String expectedSocketOut = new StringBuilder("Hello, dear friend, i'm a Oracle.").append(line)
                .append("hello").append(line).append(line)
                .append("exit").append(line).append(line)
                .toString();
        String expectedStdOut = new StringBuilder("Server: waiting command...").append(line)
                .append("Server got from client: hello").append(line)
                .append("Server: waiting command...").append(line)
                .append("Server got from client: exit").append(line)
                .toString();
        this.testServer(inString, expectedSocketOut, expectedStdOut);
    }

    /**
     * Дублирующийся код, вынесенный из тестов в отдельный метод.
     * В конструктор сервера передается сокет, методы которого переопределены,
     * с добавлением желаемой функциональности.
     *
     * @param in      ожидаемые данные из потока ввода сокета.
     * @param sockOut данные в поток вывода сокета.
     * @param stdOut  данные, выводящиеся в стандартный поток вывода сервером.
     * @throws IOException бросается при возникновении исключений при работе с потоками ввода и вывода.
     */
    private void testServer(String in, String sockOut, String stdOut) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream socketOut = new ByteArrayOutputStream();
        ByteArrayInputStream socketIn = new ByteArrayInputStream(in.getBytes());
        when(socket.getOutputStream()).thenReturn(socketOut);
        when(socket.getInputStream()).thenReturn(socketIn);
        Server server = new Server(socket);
        server.startServer();
        Assert.assertThat(socketOut.toString(), is(sockOut));
        Assert.assertThat(this.byteout.toString(), is(stdOut));
    }
}