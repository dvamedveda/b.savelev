package ru.job4j.io.minicmd;

import java.util.LinkedList;
import java.util.List;

/**
 * Класс эмулирует работу терминала с путями.
 */
class Shell {

    private List<String> resultPathParts = new LinkedList<String>();

    /**
     * Метод для перехода в какую-либо директорию.
     *
     * @param path путь для перехода
     * @return объект эмулятора терминала
     */
    public Shell cd(final String path) {
        if (path.startsWith("/")) {
            resultPathParts.clear();
        }
        String[] inPathParts = path.split("/");
        for (String part : inPathParts) {
            if (part.equals(".") || part.equals("")) {
                continue;
            }
            if (part.equals("..")) {
                resultPathParts.remove(resultPathParts.size() - 1);
            } else {
                resultPathParts.add(part);
            }
        }
        return this;
    }

    /**
     * Метод возвращает текущий рабочий каталог терминала.
     *
     * @return строка пути
     */
    public String path() {
        String result = String.join("/", resultPathParts);
        System.out.println("/" + result);
        return "/" + result;
    }
}