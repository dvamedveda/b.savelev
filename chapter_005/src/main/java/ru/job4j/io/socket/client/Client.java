package ru.job4j.io.socket.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Класс содержит реализацию клиентской части бота-оракула.
 */
public class Client {
    /**
     * Хранимый сокет.
     */
    private Socket client;

    /**
     * Конструктор класса клиента
     *
     * @param socket передаваемый извне сокет.
     */
    public Client(Socket socket) {
        this.client = socket;
    }

    /**
     * Метод с логикой запуска и работы клиентской части оракула.
     */
    public void startClient() {
        try (PrintWriter out = new PrintWriter(this.client.getOutputStream());
             BufferedReader in = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            String question;
            do {
                System.out.println("Client: Ask your question to Oracle...");
                question = console.readLine();
                out.println(question);
                out.flush();
                /*
                    while (!(str = in.readLine()).isEmpty()) приводит к фейлу билда мавеном, с появлением ошибки
                    "Inner assignments should be avoided.", поэтому условие повторения чтения ответов с сервера
                    было переписано.
                 */
                boolean hasAnswer = true;
                while (hasAnswer) {
                    String answer = in.readLine();
                    if (!answer.isEmpty()) {
                        System.out.println("Client got from server: " + answer);
                    } else {
                        hasAnswer = false;
                    }
                }
            } while (!question.equals("exit"));
            System.out.println("Client: Oracle fade away...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}