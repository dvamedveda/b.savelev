package ru.job4j.set;

import ru.job4j.list.DynamicList;

import java.util.Iterator;

/**
 * Класс, реализующий множество на основе массива с параметризуемым типом.
 * Также реализовывает Iterable, также реализовывает fail-fast поведение при итерации.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class SimpleSet<T> {
    /**
     * Хранилище данных.
     */
    private DynamicList<T> store = new DynamicList<>();

    /**
     * Проверка на содержание дубликатов во множестве.
     *
     * @param data данные.
     * @return результат поиска дубликатов.
     */
    private boolean checkDuplicates(T data) {
        Iterator<T> iterator = this.store.iterator();
        boolean result = false;
        while (iterator.hasNext()) {
            if (iterator.next().equals(data)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Метод для добавления данных во множество.
     *
     * @param data данные.
     * @return результат добавления.
     */
    public boolean add(T data) {
        boolean result = checkDuplicates(data);
        if (!result) {
            this.store.add(data);
        }
        return result;
    }

    /**
     * Итератор по коллекции.
     *
     * @return объект итератора.
     */
    public Iterator<T> iterator() {
        return this.store.iterator();
    }
}