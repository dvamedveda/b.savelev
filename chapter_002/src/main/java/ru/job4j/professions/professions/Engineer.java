package ru.job4j.professions.professions;

import ru.job4j.professions.common.Task;

/**
 * Класс, описывающий инженера.
 * Имеет разнообразные методы, определяющие его поведение.
 */
public class Engineer extends Profession {
    /**
     * Возраст.
     */
    private int age;

    /**
     * Занят ли работой в данный момент.
     */
    private boolean isWorkingNow;

    /**
     * Текущая задача.
     */
    private Task currentTask;

    /**
     * Конструктор класса Engineer.
     * Использует конструктор суперкласса для инициализации основных полей.
     * @param name имя специалиста.
     * @param specialization род деятельности специалиста.
     * @param age возраст специалиста.
     * @param isWorkingNow занят ли сейчас специалист работой.
     * @param task текущая задача специалиста.
     */
    public Engineer(String name, String specialization, int age, boolean isWorkingNow, Task task) {
        super(name, specialization);
        this.age = age;
        this.isWorkingNow = isWorkingNow;
        this.currentTask = currentTask();
    }

    /**
     * Начать работу над задачей.
     * @param task задача, которую нужно выполнить.
     */
    public void startWork(Task task) {
        this.currentTask = task;
        this.isWorkingNow = true;
    }

    /**
     * Метод, позволяющий узнать, готова ли текущая задача.
     * @param task некая задача.
     * @return false если задача не готова, true если готова.
     */
    public boolean taskIsReady(Task task) {
        return false;
    }

    /**
     * Может ли выполнять сейчас задачу.
     * @param task некая задача.
     * @return true, если может выполнять задачу, иначе false.
     */
    public boolean canDoTask(Task task) {
        return !this.isWorkingNow;
    }

    /**
     * Прекратить работу и вернуть задачу, которой занимался.
     * @return задача, над которой производилась работа.
     */
    public Task stopWork() {
        this.isWorkingNow = false;
        return this.currentTask;
    }

    /**
     * Представиться.
     * @return описание себя.
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

    /**
     * Сменить задачу на другую.
     * @param task задача, на которую нужно переключиться.
     */
    public void changeTask(Task task) {
        this.currentTask = task;
    }

    /**
     * Узнать текущую задачу.
     * @return текущая задача.
     */
    public Task currentTask() {
        return this.currentTask;
    }
}
