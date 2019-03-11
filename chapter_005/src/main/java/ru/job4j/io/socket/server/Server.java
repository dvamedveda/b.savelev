package ru.job4j.io.socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Класс содержит реализацию серверной части бота-оракула.
 */
public class Server {
    /**
     * Хранимый сокет.
     */
    private final Socket server;

    /**
     * Конструктор класса сервера.
     *
     * @param socket передаваемый извне сокет.
     */
    public Server(Socket socket) {
        this.server = socket;
    }

    /**
     * Метод, реализующий запуск и логику работы серверной части оракула.
     */
    public void startServer() {
        try (PrintWriter out = new PrintWriter(this.server.getOutputStream());
             BufferedReader in = new BufferedReader(new InputStreamReader(this.server.getInputStream()))) {
            String ask;
            do {
                System.out.println("Server: waiting command...");
                ask = in.readLine();
                if (ask.equals("hello")) {
                    out.println("Hello, dear friend, i'm a Oracle.");
                }
                out.println(ask);
                out.println();
                out.flush();
                System.out.println("Server got from client: " + ask);
            } while (!ask.equals("exit"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}