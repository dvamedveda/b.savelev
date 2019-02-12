package ru.job4j.io.abuse;

import java.io.*;

/**
 * Класс содержит методы для обработки запрещенных слов.
 */
public class Abuse {

    /**
     * Метод заменяет запрещенные слова в потоке ввода на звездочки и отправляет результат в поток вывода.
     *
     * @param in     поток ввода.
     * @param out    поток вывода.
     * @param abuses массив запрещенных слов.
     */
    public void dropAbuses(InputStream in, OutputStream out, String[] abuses) {
        String line;
        try (BufferedReader bReader = new BufferedReader(new InputStreamReader(in));
             BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(out))) {
            do {
                line = bReader.readLine();
                for (String abuse : abuses) {
                    line = line.replaceAll(abuse, "***");
                }
                bWriter.write(line);
            } while (bReader.ready());
            bWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}