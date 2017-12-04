package ru.job4j.professions.professions;

import ru.job4j.professions.common.Student;
import ru.job4j.professions.common.Task;

/**
 * Вспомогательный класс, описывающий учителя.
 */
public class Teacher extends Profession {
    /**
     * Возраст.
     */
    private int age;

    /**
     * Обучает ли кого-то в данный момент.
     */
    private boolean isTeaching;

    /**
     * Кого обучает в данный момент.
     */
    private Student whoTeached;

    public Teacher(String name, String specialization, int age, boolean isTeaching, Student whoTeached) {
        super(name, specialization);
        this.age = age;
        this.isTeaching = isTeaching;
        this.whoTeached = whoTeached;
    }

    /**
     * Выдать домашнее задание.
     * @param task домашнее задание.
     * @param student студент, которому выдается задание.
     */
    public void giveHomework(Task task, Student student) {

    }

    /**
     * Проверить домашнее задание.
     * @param task домашнее задание.
     * @param student студент, у которого проверяется домашнее задание.
     * @return результат проверки.
     */
    public boolean checkHomework(Task task, Student student) {
        return false;
    }

    /**
     * Проверка, занят ли сейчас учитель обучением.
     * @return возвращается true, если учитель занят, иначе false.
     */
    public boolean isTeachingNow() {
        return this.isTeaching;
    }

    /**
     * Поставить оценку ученику.
     * @param score оценка, которую выставляем.
     * @param student ученик, который получает оценку.
     */
    public void setScore(int score, Student student) {

    }

    /**
     * Проверяет, кто обучается в данный момент.
     * @return ученик, обучающийся в данный момент.
     */
    public Student whoTeachedNow() {
        return this.whoTeached;
    }

    /**
     * Начать обучение ученика.
     * @param student ученик, которого нужно обучать.
     */
    public void teach(Student student) {

    }

    /**
     * Прекратить обучение.
     */
    public void stopTeach() {

    }

    /**
     * Представиться.
     * @return возвращает описание себя.
     */
    public String introduceSelf() {
        String years;
        if (this.age % 10 == 1) {
            years = " год.";
        } else if (this.age % 10 > 1 && this.age < 5) {
            years = " года.";
        } else {
            years = " лет.";
        }

        return super.introduceSelf() + " Мне " + Integer.toString(this.age) + years;
    }
}