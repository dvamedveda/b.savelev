package ru.job4j.io.chat;


import org.apache.log4j.Logger;

import java.io.*;

/**
 * Класс с логикой работы консольного чата.
 */
public class Logic {
    /**
     * Поле для хранения класса ответов.
     */
    private Answer answers;

    /**
     * Поле для хранения используемого потока ввода.
     */
    private InputStream ui;

    /**
     * Поле для хранения используемого потока вывода.
     */
    private OutputStream out;

    /**
     * Флаг завершения программы.
     */
    private boolean exit = false;

    /**
     * Флаг остановки вывода ответов чата.
     */
    private boolean stop = false;

    /**
     * Класс логгера для логирования сообщений из чата.
     */
    final static Logger LOGGER = Logger.getLogger(Logic.class);

    /**
     * Конструктор класса логики чата.
     *
     * @param path   путь к файлу с ответами.
     * @param input  используемый поток ввода данных для чата.
     * @param output используемый поток вывода данных для чата.
     */
    public Logic(String path, InputStream input, OutputStream output) {
        this.answers = new Answer(path);
        this.ui = input;
        this.out = output;
    }

    /**
     * Основной метод с логикой работы чата.
     *
     * @throws IOException кидается, если в процессе работы с потоками ввода и вывода появляется исключение.
     */
    public void start() throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(this.out));
        BufferedReader reader = new BufferedReader(new InputStreamReader(this.ui));
        writer.write("Hello!");
        writer.newLine();
        writer.flush();
        String nextQuestion;
        do {
            nextQuestion = reader.readLine();
            String answer = answers.getAnswer();
            if (nextQuestion.equals("stop")) {
                this.stop = true;
            } else if (nextQuestion.equals("finish")) {
                this.exit = true;
                answer = "Finished";
            } else if (nextQuestion.equals("resume")) {
                this.stop = false;
                answer = "Resumed";
            }
            LOGGER.info("User input: " + nextQuestion);
            if (!stop) {
                writer.write(answer);
                writer.newLine();
                writer.flush();
                LOGGER.info("Bot answer: " + answer);
            }
        } while (!exit);
    }
}