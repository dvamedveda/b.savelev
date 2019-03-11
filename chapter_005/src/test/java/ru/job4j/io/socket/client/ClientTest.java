package ru.job4j.io.socket.client;

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
 * Класс содержит тесты класса ru.job4j.io.socket.client.Client.
 */
public class ClientTest {
    /**
     * Читающийся в тестах байтовый поток вывода.
     */
    private OutputStream byteout = new ByteArrayOutputStream();

    /**
     * Сохранение стандартного потока вывода.
     */
    private PrintStream stdout = System.out;

    /**
     * Читающийся в тестах байтовый поток ввода.
     */
    private InputStream bytein;

    /**
     * Сохранение стандартного потока ввода.
     */
    private InputStream stdin = System.in;

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
     * Подмена потока в стандартном потоке ввода,
     * для того, чтобы мы могли его читать.
     * Используется по требованию.
     */
    public void loadByteIn() {
        System.setIn(bytein);
    }

    /**
     * Возврат стандартного потока в поток вывода.
     * Используется по требованию.
     */
    public void loadStandartIn() {
        System.setIn(this.stdin);
    }

    /**
     * Проверка выхода из клиентской части при получении сообщения "exit".
     *
     * @throws IOException бросается при возникновении исключений при работе с потоками сокета.
     */
    @Test
    public void whenReceiveExitThenClientExits() throws IOException {
        String socketIn = new StringBuilder()
                .append("exit").append(line)
                .append(line)
                .toString();
        String console = new StringBuilder()
                .append("exit").append(line)
                .toString();
        String expectedSocketOut = new StringBuilder()
                .append("exit").append(line)
                .toString();
        String expectedStdOut = new StringBuilder("Client: Ask your question to Oracle...").append(line)
                .append("Client got from server: exit").append(line)
                .append("Client: Oracle fade away...").append(line)
                .toString();
        this.testClient(socketIn, expectedSocketOut, console, expectedStdOut);
    }

    /**
     * Проверка, что при отправке сообщения, кроме команды на выход,
     * клиент получает от сервера свое сообщение в качестве полезной работы.
     *
     * @throws IOException бросается при возникновении исключений при работе с потоками сокета.
     */
    @Test
    public void whenSendMessageThenReceivesIt() throws IOException {
        String socketIn = new StringBuilder()
                .append("hi").append(line)
                .append(line)
                .append("exit").append(line)
                .append(line)
                .toString();
        String console = new StringBuilder()
                .append("hi").append(line)
                .append("exit").append(line)
                .toString();
        String expectedSocketOut = new StringBuilder()
                .append("hi").append(line)
                .append("exit").append(line)
                .toString();
        String expectedStdOut = new StringBuilder("Client: Ask your question to Oracle...").append(line)
                .append("Client got from server: hi").append(line)
                .append("Client: Ask your question to Oracle...").append(line)
                .append("Client got from server: exit").append(line)
                .append("Client: Oracle fade away...").append(line)
                .toString();
        this.testClient(socketIn, expectedSocketOut, console, expectedStdOut);
    }

    /**
     * Дублирующийся код, вынесенный из тестов в отдельный метод.
     * В конструктор клиента передается сокет, методы которого переопределены,
     * с добавлением желаемой функциональности.
     *
     * @param sockIn  данные для потока ввода в сокет.
     * @param sockOut ожидаемые данные от потока вывода сокета.
     * @param console данные, полученные от пользователя.
     * @param stdOut  данные, напечатанные в консоль клиентской частью оракула.
     * @throws IOException бросается при возникновении исключений при работе с потоками ввода/вывода.
     */
    private void testClient(String sockIn, String sockOut, String console, String stdOut) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream socketOut = new ByteArrayOutputStream();
        ByteArrayInputStream socketIn = new ByteArrayInputStream(sockIn.getBytes());
        this.bytein = new ByteArrayInputStream(console.getBytes());
        when(socket.getOutputStream()).thenReturn(socketOut);
        when(socket.getInputStream()).thenReturn(socketIn);
        loadByteIn();
        Client client = new Client(socket);
        client.startClient();
        Assert.assertThat(socketOut.toString(), is(sockOut));
        Assert.assertThat(this.byteout.toString(), is(stdOut));
        loadStandartIn();
    }
}