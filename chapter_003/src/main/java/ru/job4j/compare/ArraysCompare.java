package ru.job4j.compare;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

/**
 * Класс содержащий методы для сравнения производительности коллекций.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class ArraysCompare {

    /**
     * Метод, выполняющий вставку заданного количества случайных строк в произвольную коллекцию.
     *
     * @param collection коллекция, которую нужно заполнить.
     * @param amount     количество элементов, которыми нужно заполнить коллекцию.
     * @return время заполнения в мс
     */
    public long add(Collection<String> collection, int amount) {
        Random randomizer = new Random();
        long start = System.currentTimeMillis();
        for (int count = 0; count < amount; count++) {
            int randomInt = randomizer.nextInt(10000);
            collection.add(String.format("%s%s", Integer.toString(randomInt), Long.toString(System.currentTimeMillis())));
        }
        long finish = System.currentTimeMillis();
        return finish - start;
    }

    /**
     * Метод, выполняющий удаление первых N элементов из произвольной коллекции.
     *
     * @param collection коллекция, из которой нужно удалять элементы.
     * @param amount     количество элементов, которые нужно удалить.
     * @return время удаления в мс
     */
    public long delete(Collection<String> collection, int amount) {
        long start = System.currentTimeMillis();
        Iterator<String> iterator = collection.iterator();
        for (int count = 0; count < amount; count++) {
            iterator.next();
            iterator.remove();
        }
        long finish = System.currentTimeMillis();
        return finish - start;
    }
}