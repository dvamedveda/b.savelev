package ru.job4j.io.archive;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 * Класс содержит основные методы для архивации проекта.
 */
public class Archive {
    /**
     * Системный разделитель путей.
     */
    private String slash = System.getProperty("file.separator");
    /**
     * Системный каталог для временных файлов.
     */
    private File root = new File(System.getProperty("java.io.tmpdir"));

    /**
     * Метод возвращает отфильтрованный список файлов по указанному пути.
     * Файлы фильтруются в соответствии с заданным списком расширений для исключения.
     *
     * @param directory  путь для сбора списка файлов.
     * @param extensions массив расширений, файлы с которыми нужно исключить из списка.
     * @return отфильтрованный список объектов File.
     */
    public List<File> filterFiles(String directory, String[] extensions) {
        LinkedList<File> result = new LinkedList<>();
        Queue<File> queue = new LinkedList<>();
        File root = new File(directory);
        for (File file : root.listFiles()) {
            queue.offer(file);
        }
        while (!queue.isEmpty()) {
            File nextFileEntry = queue.poll();
            if (nextFileEntry.isDirectory()) {
                for (File file : nextFileEntry.listFiles()) {
                    queue.offer(file);
                }
                result.add(nextFileEntry);
            } else {
                String fileName = nextFileEntry.getName();
                boolean forbidden = false;
                int index = fileName.lastIndexOf(".") == -1 ? 0 : fileName.lastIndexOf(".");
                String ext = fileName.substring(index);
                for (String extension : extensions) {
                    if (extension.equals(ext)) {
                        forbidden = true;
                    }
                }
                if (!forbidden) {
                    result.add(nextFileEntry);
                }
            }
        }
        return result;
    }

    /**
     * Метод для записи списка файлов в файл архива.
     * Архив записывается в ZIP формате на лету.
     *
     * @param files список объектов File
     * @param out   путь до файла с архивом.
     * @throws IOException может быть выброшено при работе с файлами.
     */
    public void writeArchive(List<File> files, String out) throws IOException {
        File outFile = new File(out);
        if (!outFile.isAbsolute()) {
            outFile = new File(root + slash + out);
        }
        outFile.createNewFile();
        ZipOutputStream zout = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outFile)));
        for (File file : files) {
            String relPath = file.getPath().replace(file.getParent() + slash, "");
            zout.putNextEntry(new ZipEntry(relPath));
            if (!file.isDirectory()) {
                BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file));
                while (fin.available() > 0) {
                    zout.write(fin.read());
                }
                fin.close();
            }
            zout.flush();
            zout.closeEntry();
        }
        zout.close();
    }
}