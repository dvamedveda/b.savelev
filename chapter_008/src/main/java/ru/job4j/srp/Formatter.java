package ru.job4j.srp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        List<String> lines = Arrays.asList(text.split(System.lineSeparator()));
        if (format == Format.HTML) {
            result = formatByHtml(lines);
        } else if (format == Format.JSON) {
            result = formatByJson(lines);
        } else if (format == Format.XML) {
            result = formatByXml(lines);
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

    /**
     * Метод для форматирования строки со списком сотрудников в json.
     *
     * @param lines список сотрудников в виде списка строк.
     * @return отформатированная в json строка.
     */
    private static String formatByJson(List<String> lines) {
        StringBuilder result = new StringBuilder();
        result.append("{");
        List<String> names = Arrays.stream(lines.get(0).split(";")).map(String::trim).collect(Collectors.toList());
        for (int i = 1; i < lines.size(); i++) {
            List<String> values = Arrays.stream(lines.get(i).split(";")).map(String::trim).collect(Collectors.toList());
            result.append("\"").append(values.get(0)).append("\": {");
            for (int valIndex = 1; valIndex < values.size(); valIndex++) {
                result.append("\"").append(names.get(valIndex)).append("\": \"").append(values.get(valIndex));
                if (valIndex < values.size() - 1) {
                    result.append("\",");
                } else {
                    result.append("\"");
                }
            }
            if (i < lines.size() - 1) {
                result.append("},");
            } else {
                result.append("}");
            }
        }
        result.append("}");
        return result.toString();
    }

    /**
     * Метод для форматирования строки со списком сотрудников в xml.
     *
     * @param lines список сотрудников в виде списка строк.
     * @return отформатированная в xml строка.
     */
    private static String formatByXml(List<String> lines) {
        StringBuilder result = new StringBuilder();
        result.append("<employees>");
        List<String> names = Arrays.stream(lines.get(0).split(";")).map(String::trim).collect(Collectors.toList());
        for (int i = 1; i < lines.size(); i++) {
            List<String> values = Arrays.stream(lines.get(i).split(";")).map(String::trim).collect(Collectors.toList());
            result.append("<employee>");
            for (int valIndex = 0; valIndex < values.size(); valIndex++) {
                result.append("<").append(names.get(valIndex).toLowerCase()).append(">")
                        .append(values.get(valIndex))
                        .append("</").append(names.get(valIndex).toLowerCase()).append(">");
            }
            result.append("</employee>");
        }
        result.append("</employees>");
        return result.toString();
    }
}