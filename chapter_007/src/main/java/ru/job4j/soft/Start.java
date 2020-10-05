package ru.job4j.soft;

import java.io.*;

/**
 * Просто приложение для демонстрации работы программы.
 * Можно ввести путь к файлу на системном диске и кэш его загрузит.
 */
public class Start {
    public static void main(String[] args) throws IOException {
        Cache cache = new SoftCache("./"); // just type "chapter_007/src/test/resources/test_file_one.txt"
        try (
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String ask;
            while (true) {
                System.out.println("Enter path to file...");
                ask = reader.readLine();
                if (ask.equals("exit")) {
                    System.out.println("Bye!");
                    break;
                } else {
                    out.write(cache.get(ask));
                    out.flush();
                }
            }
        }
    }
}