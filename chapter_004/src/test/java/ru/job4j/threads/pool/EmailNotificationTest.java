package ru.job4j.threads.pool;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Класс, реализующий тесты для класса ru.job4j.threads.pool.EmailNotification.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class EmailNotificationTest {

    /**
     * Проверка отправки письма через ExecutorService.
     */
    @Test
    public void whenEmailToUserThenEmailSendSuccess() {
        EmailNotification emailNotification = new EmailNotification();
        User user = new User("Dima", "dima@yandex.ru");
        String result = emailNotification.emailTo(user);
        String expected = "Email to dima@yandex.ru sent! Subject: Notification Dima to email dima@yandex.ru.; Body: Add a new event to Dima";
        emailNotification.close();
        assertThat(result, is(expected));
    }
}
