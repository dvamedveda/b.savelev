package ru.job4j.io.socket.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Класс для запуска серверной части бота-оракла.
 */
public class Start {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new ServerSocket(3000).accept()) {
            Server server = new Server(socket);
            server.startServer();
        }
    }
}