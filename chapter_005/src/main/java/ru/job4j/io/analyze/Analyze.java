package ru.job4j.io.analyze;

import java.io.*;
import java.time.LocalTime;
import java.util.Map;
import java.util.TreeMap;

/**
 * Класс для анализа логов доступности сервера.
 */
public class Analyze {

    /**
     * Метод анализирующий доступность сервера по логам. Записывает результат работы в отдельный файл.
     *
     * @param source путь к файлу логов для анализа.
     * @param target путь к файлу, куда будет записан результат анализа.
     */
    public void unavailable(String source, String target) {
        Map<LocalTime, Integer> events = parseLog(source);
        analyzePeriods(events, target);
    }

    /**
     * Вспомогательный метод, который парсит логи в карту событий.
     *
     * @param source путь к файлу логов.
     * @return карта событий на сервере.
     */
    private TreeMap<LocalTime, Integer> parseLog(String source) {
        TreeMap<LocalTime, Integer> events = new TreeMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            String line;
            while (reader.ready()) {
                line = reader.readLine();
                LocalTime time = LocalTime.parse(line.substring(line.indexOf(';') + 1));
                Integer code = Integer.parseInt(line.substring(0, line.indexOf(';')));
                events.put(time, code);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return events;
    }

    /**
     * Вспомогательный метод, выбирающий периоды недоступности сервера из карты событий сервера и записывающий их
     * в файл с результатами.
     *
     * @param events карта событий сервера.
     * @param target файл результатов анализа.
     */
    private void analyzePeriods(Map<LocalTime, Integer> events, String target) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(target))) {
            boolean isPeriod = false;
            StringBuilder line = new StringBuilder();
            for (Map.Entry<LocalTime, Integer> entry : events.entrySet()) {
                if ((entry.getValue() == 400 | entry.getValue() == 500) && (!isPeriod)) {
                    isPeriod = true;
                    line.append(entry.getKey().toString()).append(";");
                } else if ((entry.getValue() == 200 | entry.getValue() == 300) && (isPeriod)) {
                    isPeriod = false;
                    line.append(entry.getKey().toString()).append(";");
                    writer.println(line);
                    line = new StringBuilder();
                }
            }
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}