package ru.job4j.threads.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Класс, реализующий отправку почты через ExecutorService.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class EmailNotification {
    /**
     * Пул потоков фиксированного(по числу ядер в системе) размера.
     */
    private ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * Отправка почты посредством ExecutorService.
     *
     * @param user юзер, которому отсылается почта
     * @return результат отправки письма
     */
    public String emailTo(User user) {
        String email = user.getEmail();
        String name = user.getUser();
        String subject = String.format("Notification %s to email %s.", name, email);
        String body = String.format("Add a new event to %s", name);
        this.pool.submit(() -> send(subject, body, email));
        return String.format("Email to %s sent! Subject: %s; Body: %s", email, subject, body);
    }

    /**
     * Метод, осуществляющий отправку письма.
     *
     * @param subject тема письма.
     * @param body    тело письма.
     * @param email   почта для отправки.
     */
    private void send(String subject, String body, String email) {
        System.out.println(String.format("Email to %s sent! Subject: %s; Body: %s", email, subject, body));
    }

    /**
     * Закрытие пула с ожиданием завершения задач в нем.
     */
    public void close() {
        this.pool.shutdown();
        while (!this.pool.isTerminated()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
