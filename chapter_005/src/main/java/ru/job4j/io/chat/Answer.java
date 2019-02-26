package ru.job4j.io.chat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Класс содержит методы для работы с файлом ответов консольного чата.
 */
public class Answer {
    /**
     * Поле для хранения объекта файла с ответами.
     */
    private File answersFile;

    /**
     * Поле для хранения списка всех ответов.
     */
    private ArrayList<String> answers;

    /**
     * Вспомогательное поле-рандомайзер.
     */
    private Random random = new Random();

    /**
     * Конструктор класса ответов.
     * Требует путь к файлу с ответами.
     *
     * @param path путь к файлу с ответами.
     */
    public Answer(String path) {
        this.answersFile = new File(path);
        this.answers = getAnswers();
    }

    /**
     * Разбор файла с ответами.
     * Все ответы добавляются в список ответов.
     *
     * @return список возможных ответов.
     */
    private ArrayList<String> getAnswers() {
        ArrayList<String> result = new ArrayList<>();
        try (BufferedReader fin = new BufferedReader(new FileReader(this.answersFile))) {
            while (fin.ready()) {
                result.add(fin.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Получить один случайный ответ из списка.
     *
     * @return строка с одним случайным ответом.
     */
    public String getAnswer() {
        return answers.get(random.nextInt(answers.size()));
    }

    /**
     * Получить список всех ответов.
     *
     * @return список ответов.
     */
    public List<String> getAnswersList() {
        return this.answers;
    }
}