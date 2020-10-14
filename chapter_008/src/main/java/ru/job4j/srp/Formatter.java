package ru.job4j.srp;

import java.util.Arrays;
import java.util.List;

/**
 * Утилитный класс для форматирования информации.
 */
public class Formatter {
    /**
     * Системный разделитель строк.
     */
    private static String lbreak = System.lineSeparator();

    /**
     * Сконвертировать зарплаты сотрудников в количество тысяч.
     *
     * @param num зарплата в единицах.
     * @return зарплата в тысячах.
     */
    public static double toThousands(double num) {
        return num / 1000;
    }

    /**
     * Форматировать список сотрудников в одном из доступных форматов.
     *
     * @param text   строка со списком сотрудников.
     * @param format формат вывода строки.
     * @return отформатированная строка.
     */
    public static String format(String text, Format format) {
        String result = "";
        if (format == Format.HTML) {
            List<String> lines = Arrays.asList(text.split(System.lineSeparator()));
            result = formatByHtml(lines);
        }
        return result;
    }

    /**
     * Метод для форматирования строки со списком сотрудников в HTML.
     *
     * @param lines список сотрудников в виде списка строк.
     * @return отформатированная в html строка.
     */
    private static String formatByHtml(List<String> lines) {
        StringBuilder result = new StringBuilder();
        result.append("<html>").append(lbreak);
        result.append("<table>").append(lbreak);
        for (int i = 0; i < lines.size(); i++) {
            result.append("<tr>");
            if (i == 0) {
                Arrays.stream(lines.get(0).split(";"))
                        .map(String::trim).map((s -> "<th>" + s + "</th>"))
                        .forEachOrdered(result::append);
            } else {
                Arrays.stream(lines.get(i).split(";"))
                        .map(String::trim).map(s -> "<td>" + s + "</td>")
                        .forEachOrdered(result::append);
            }
            result.append("</tr>");
        }
        result.append("</table>").append(lbreak);
        result.append("</html>").append(lbreak);
        return result.toString();
    }
}