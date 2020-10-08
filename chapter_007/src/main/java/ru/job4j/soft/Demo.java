package ru.job4j.soft;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Еще один класс для наблюдения за работой сборщика мусора при помощи кеша.
 * Он получает список путей к файлам из специального файла
 * И затем этот список загружается в кеш на протяжении длительного времени.
 */
public class Demo {
    public static void main(String[] args) {
        SoftCache cache = new SoftCache(".\\");
        List<String> files = getFiles(".\\System\\index.all");
        for (String file : files) {
            cache.get(file);
            System.out.println("Loaded: " + file);
        }
    }

    /**
     * Получение списка путей из файла.
     * @param path путь к файлу с путями.
     * @return список путей.
     */
    public static List<String> getFiles(String path) {
        ArrayList<String> result = new ArrayList<>();
        String line = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while (reader.ready()) {
                line = reader.readLine();
                if (line.contains("\t")) {
                    line = line.split("\t")[1];
                    result.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}