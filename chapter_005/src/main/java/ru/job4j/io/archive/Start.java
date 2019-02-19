package ru.job4j.io.archive;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Класс содержит метод для запуска программы-архиватора.
 */
public class Start {
    public static void main(String[] args) {
        Archive archive = new Archive();
        Args params = new Args(args);

        if (!params.hasAllOption() || params.getDirectory().isEmpty()
                || params.getOutput().isEmpty()
                || params.getExcludes().length == 0) {
            params.printHelp();
        } else {
            List<File> files = archive.filterFiles(params.getDirectory(), params.getExcludes());
            try {
                archive.writeArchive(files, params.getOutput());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}