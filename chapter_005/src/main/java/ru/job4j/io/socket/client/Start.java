package ru.job4j.io.socket.client;

import java.io.IOException;
import java.net.Socket;

/**
 * Класс для запуска клиентской части оракула.
 */
public class Start {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 3000)) {
            Client client = new Client(socket);
            client.startClient();
        }
    }
}