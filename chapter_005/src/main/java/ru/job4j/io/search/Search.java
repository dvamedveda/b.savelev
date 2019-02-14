package ru.job4j.io.search;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Класс содержит методы для работы с файловой системой.
 */
public class Search {

    /**
     * Метод для получения списка всех файлов с заданным расширением по указанному пути.
     * Все внутренние каталоги обходятся в ширину.
     *
     * @param parent путь, в котором ищутся файлы.
     * @param exts   список расширений файлов, которые надо найти.
     * @return список найденных файлов.
     */
    public List<File> files(String parent, List<String> exts) {
        LinkedList<File> result = new LinkedList<>();
        Queue<File> queue = new LinkedList<>();
        File root = new File(parent);
        queue.offer(root);
        while (!queue.isEmpty()) {
            File nextFileEntry = queue.poll();
            if (nextFileEntry.isDirectory()) {
                for (File file : nextFileEntry.listFiles()) {
                    queue.offer(file);
                }
            } else {
                String fileName = nextFileEntry.getName();
                int index = fileName.lastIndexOf(".") == -1 ? 0 : fileName.lastIndexOf(".");
                String ext = fileName.substring(index);
                for (String extension : exts) {
                    if (extension.equals(ext)) {
                        result.add(nextFileEntry);
                        break;
                    }
                }
            }
        }
        return result;
    }
}