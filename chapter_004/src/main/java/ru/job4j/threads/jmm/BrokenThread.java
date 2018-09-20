package ru.job4j.threads.jmm;

/**
 * Приложение иллюстрирующее обе проблемы, возникающие при многопоточности:
 * первая проблема - data visibility, вторая - race condition.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class BrokenThread {

    /**
     * Переменная-счетчик, значение которой будет обновляться двумя потоками.
     */
    private int counter = 0;

    /**
     * Метод для увеличения значения переменной.
     */
    private void incrementCounter() {
        counter++;
    }

    /**
     * Инкремент переменной в двух потоках по миллиону раз в каждом.
     * В конце работы метод выводит итоговое состояние счетчика на печать, которое должно быть равным 2 000 000.
     */
    private void doCount() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                incrementCounter();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                incrementCounter();
            }
        });
        t1.start();
        t2.start();

        while (t1.isAlive() || t2.isAlive()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(counter);
    }

    public static void main(String[] args) {
        new BrokenThread().doCount();
    }
}
