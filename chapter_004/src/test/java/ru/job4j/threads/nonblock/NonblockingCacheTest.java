package ru.job4j.threads.nonblock;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.Matchers.is;

/**
 * Класс, реализующий тесты для пакета ru.job4j.threads.nonblock.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class NonblockingCacheTest {

    /**
     * Здесь проверяется обновление одного объекта и его версии последовательно из одного потока.
     */
    @Test
    public void whenUpdateBaseWithOneThreadThenVersionChanged() {
        NonblockingCache cache = new NonblockingCache();
        Base first = new Base(1, "First");
        cache.add(first);
        cache.update(cache.get(1), "FirstTwo");
        Assert.assertThat(cache.get(first.getId()).getVersion(), is(1));
        cache.update(cache.get(1), "FirstThree");
        Assert.assertThat(cache.get(first.getId()).getVersion(), is(2));
    }

    /**
     * Здесь проверяется, что при добавлении объекта у него нулевая версия.
     */
    @Test
    public void whenAddBaseThenVersionIsZero() {
        NonblockingCache cache = new NonblockingCache();
        Base first = new Base(1, "First");
        cache.add(first);
        Assert.assertThat(cache.get(first.getId()).getVersion(), is(0));
    }

    /**
     * Здесь проверяется удаление объекта из хранилища.
     */
    @Test
    public void whenDeletingBaseThenDeleting() {
        NonblockingCache cache = new NonblockingCache();
        Base first = new Base(1, "First");
        cache.add(first);
        cache.delete(first);
        Assert.assertThat(cache.get(first.getId()).getVersion(), is(0));
        Assert.assertThat(cache.get(first.getId()).getId(), is(-1));
        Assert.assertThat(cache.get(first.getId()).getName(), is("default"));
    }

    /**
     * Здесь проверяется добавление нескольких объектов в хранилище.
     */
    @Test
    public void whenAddThreeBaseThenThreeBasesIsExists() {
        NonblockingCache cache = new NonblockingCache();
        Base first = new Base(1, "First");
        Base second = new Base(2, "Second");
        Base third = new Base(3, "Third");
        cache.add(first);
        cache.add(second);
        cache.add(third);
        Assert.assertThat(cache.get(first.getId()).getVersion(), is(0));
        Assert.assertThat(cache.get(second.getId()).getVersion(), is(0));
        Assert.assertThat(cache.get(third.getId()).getVersion(), is(0));
    }

    /**
     * Здесь проверяется добавление многих объектов из нескольких потоков.
     *
     * @throws InterruptedException
     */
    @Test
    public void whenAddBasesFromOneMoreThreadThenAddingSuccess() throws InterruptedException {
        NonblockingCache cache = new NonblockingCache();
        Thread firstThread = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                cache.add(new Base(i, Integer.toString(i)));
            }
        });
        Thread secondThread = new Thread(() -> {
            for (int i = 5000; i < 10000; i++) {
                cache.add(new Base(i, Integer.toString(i)));
            }
        });
        Thread thirdThread = new Thread(() -> {
            for (int i = 10000; i < 15000; i++) {
                cache.add(new Base(i, Integer.toString(i)));
            }
        });
        firstThread.start();
        secondThread.start();
        thirdThread.start();
        firstThread.join();
        secondThread.join();
        thirdThread.join();
        for (int i = 0; i < 15000; i++) {
            Assert.assertThat(cache.get(i).getId(), is(i));
            Assert.assertThat(cache.get(i).getVersion(), is(0));
        }
    }

    /**
     * Здесь проверяется, что если один объект будет
     * пробовать обновляться из нескольких потоков,
     * то обновляться будет только из одного потока.
     *
     * @throws InterruptedException
     */
    @Test
    public void whenUpdatingFromOneMoreThreadThenGetException() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        NonblockingCache cache = new NonblockingCache();
        Thread addingThread = new Thread(() -> {
            for (int i = 0; i < 2000; i++) {
                cache.add(new Base(i, Integer.toString(i)));
            }
        });
        addingThread.start();
        addingThread.join();
        Thread firstUpdatingThread = new Thread(() -> {
            for (int i = 0; i < 2000; i++) {
                try {
                    cache.update(cache.get(i), Integer.toString(i));
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (OptimisticException e) {
                    ex.set(e);
                    e.printStackTrace();
                }
            }
        });
        Thread secondUpdatingThread = new Thread(() -> {
            for (int i = 0; i < 2000; i++) {
                try {
                    cache.update(cache.get(i), Integer.toString(i));
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (OptimisticException e) {
                    ex.set(e);
                    e.printStackTrace();
                }
            }
        });
        Thread thirdUpdatingThread = new Thread(() -> {
            for (int i = 0; i < 2000; i++) {
                try {
                    cache.update(cache.get(i), Integer.toString(i));
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (OptimisticException e) {
                    ex.set(e);
                    e.printStackTrace();
                }
            }
        });
        firstUpdatingThread.start();
        secondUpdatingThread.start();
        thirdUpdatingThread.start();
        firstUpdatingThread.join();
        secondUpdatingThread.join();
        thirdUpdatingThread.join();
        Assert.assertThat(ex.get().getMessage(), is("Version changed! Don't update"));
    }
}