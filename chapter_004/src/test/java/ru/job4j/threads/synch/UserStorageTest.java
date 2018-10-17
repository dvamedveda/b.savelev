package ru.job4j.threads.synch;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

/**
 * Класс, реализующий тест для класса UserStorage.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class UserStorageTest {

    /**
     * Проверяем разовый перевод денег, если у источника достаточная сумма.
     */
    @Test
    public void whenTransferMoneyThenResultIsSuccess() {
        UserStorage users = new UserStorage();
        User userOne = new User(1, 100000);
        User userTwo = new User(2, 0);
        users.add(userOne);
        users.add(userTwo);
        Assert.assertThat(users.transfer(userOne.getId(), userTwo.getId(), 1), is(true));
        Assert.assertThat(users.getById(1).getAmount(), is(99999));
        Assert.assertThat(users.getById(2).getAmount(), is(1));
    }

    /**
     * Проверяем разовый перевод денег, если у источника недостаточная сумма.
     */
    @Test
    public void whenNoMoneyThenTransferFails() {
        UserStorage users = new UserStorage();
        User userOne = new User(1, 1);
        User userTwo = new User(2, 0);
        users.add(userOne);
        users.add(userTwo);
        Assert.assertThat(users.transfer(userOne.getId(), userTwo.getId(), 100), is(false));
        Assert.assertThat(users.getById(1).getAmount(), is(1));
        Assert.assertThat(users.getById(2).getAmount(), is(0));
    }

    /**
     * Проверяем многоразовый перевод денег в двух потоках.
     */
    @Test
    public void whenMultiplyTransferingThenResultSame() throws InterruptedException {
        UserStorage users = new UserStorage();
        User userOne = new User(1, 100000);
        User userTwo = new User(2, 0);
        users.add(userOne);
        users.add(userTwo);
        Thread firstThread = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                users.transfer(userOne.getId(), userTwo.getId(), 1);
            }
        });
        Thread secondThread = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                users.transfer(userOne.getId(), userTwo.getId(), 1);
            }
        });
        firstThread.start();
        secondThread.start();
        firstThread.join();
        secondThread.join();
        Assert.assertThat(userOne.getAmount(), is(0));
        Assert.assertThat(userTwo.getAmount(), is(100000));
    }

    /**
     * Проверяем добавление пользователя в хранилище.
     */
    @Test
    public void whenAddUserThenAddingSuccess() {
        UserStorage users = new UserStorage();
        User userOne = new User(1, 100000);
        Assert.assertThat(users.add(userOne), is(true));
        Assert.assertThat(users.getById(1), is(userOne));
    }

    /**
     * Проверяем многоразовый перевод денег в нескольких потоках с одновременным добавлением пользователей в третьем потоке.
     */
    @Test
    public void whenMultiplyAddUsersAndTransfersMoneyThenSuccess() throws InterruptedException {
        UserStorage users = new UserStorage();
        User userOne = new User(1, 200000);
        User userTwo = new User(2, 0);
        users.add(userOne);
        users.add(userTwo);
        Thread firstThread = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                users.transfer(userOne.getId(), userTwo.getId(), 1);
            }
        });
        Thread secondThread = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                users.transfer(userOne.getId(), userTwo.getId(), 1);
            }
        });
        Thread thirdThread = new Thread(() -> {
            for (int i = 3; i <= 1000; i++) {
                users.add(new User(i, i));
            }
        });
        firstThread.start();
        secondThread.start();
        thirdThread.start();
        firstThread.join();
        secondThread.join();
        thirdThread.join();
        for (int i = 1; i <= 1000; i++) {
            if (i == 1) {
                Assert.assertThat(users.getById(i).getAmount(), is(0));
            } else if (i == 2) {
                Assert.assertThat(users.getById(i).getAmount(), is(200000));
            } else {
                Assert.assertThat(users.getById(i).getAmount(), is(i));
            }
        }
    }

    /**
     * Проверяем успешное удаление пользователя из хранилища.
     */
    @Test
    public void whenDeleteUserThenDeletingSuccess() {
        UserStorage users = new UserStorage();
        User userOne = new User(1, 100000);
        users.add(userOne);
        Assert.assertThat(users.delete(userOne), is(true));
        Assert.assertNull(users.getById(1));
    }

    /**
     * Проверяем успешное удаление пользователя из хранилища.
     */
    @Test
    public void whenNoUserThenDeletingFails() {
        UserStorage users = new UserStorage();
        User userOne = new User(1, 100000);
        Assert.assertNull(users.getById(1));
        Assert.assertThat(users.delete(userOne), is(false));
    }
}