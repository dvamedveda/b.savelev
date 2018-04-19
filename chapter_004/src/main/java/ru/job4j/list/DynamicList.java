package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс, реализующий контейнер DynamicList с параметризуемым типом, использующий обычный массив.
 * Также реализовывает Iterable, также реализовывает fail-fast поведение при итерации.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class DynamicList<T> implements Iterable<T> {

    /**
     * Здесь хранится массив с данными
     */
    private Object[] store = new Object[10];

    /**
     * Счетчик модификаций, копирующийся в итератор.
     */
    private int modCount = 0;

    /**
     * Добавление элемента в список.
     * Объект добавляется в конец списка.
     *
     * @param model объект для добавления.
     */
    public void add(T model) {
        int index = this.emptyIndex();
        this.store[index] = model;
        this.modCount++;
    }

    /**
     * Получить элемент списка по заданному индексу.
     *
     * @param index индекс ячейки.
     * @return значение заданной ячейки
     */
    public T get(int index) {
        T result = null;
        if (this.store[index] != null) {
            result = (T) this.store[index];
        }
        return result;
    }

    /**
     * Выдает первый свободный индекс в массиве для добавления элемента.
     * Если свободных индексов нет, размер массива увеличивается наполовину,
     * если количество элементов нечетное - то наполовину с округлением в меньшую сторону
     * и возвращается первый свободный индекс.
     *
     * @return свободный индекс.
     */
    private int emptyIndex() {
        int result = -1;
        for (int count = 0; count < this.store.length; count++) {
            if (this.store[count] == null) {
                result = count;
                break;
            }
            if (count == this.store.length - 1 && this.store[count] != null) {
                Object[] temp = this.store;
                int length = this.store.length;
                this.store = new Object[length % 2 == 0 ? length + (length / 2) : length + ((length - 1) / 2)];
                System.arraycopy(temp, 0, this.store, 0, temp.length);
                result = count + 1;
            }
        }
        return result;
    }

    /**
     * Удалить элемент списка по заданному индексу.
     * При удалении элемента часть списка справа от индекса перемещается на одну позицию влево.
     *
     * @param index индекс, по которому удаляется элемент.
     */
    public void delete(int index) {
        Object[] result = new Object[this.store.length];
        if (index == 0) {
            System.arraycopy(store, 1, result, 0, this.store.length - 1);
        } else if (index == store.length - 1) {
            System.arraycopy(this.store, 0, result, 0, this.store.length - 1);
        } else {
            System.arraycopy(store, 0, result, 0, index);
            System.arraycopy(store, index + 1, result, index, this.store.length - index - 1);
        }
        this.store = result;
        this.modCount++;
    }

    /**
     * Получить длину списка с данными.
     *
     * @return длина списка.
     */
    public int length() {
        return this.store.length;
    }

    public void set(int index, T model) {
        this.store[index] = model;
        this.modCount++;
    }

    /**
     * Отдаем итератор для SimpleArray
     *
     * @return объект итератора.
     */
    @Override
    public Iterator<T> iterator() {
        return new DynamicListIterator();
    }

    /**
     * Здесь реализуется итератор по SimpleArray.
     */
    private class DynamicListIterator implements Iterator<T> {

        private int index = 0;
        private Object[] array = store;
        private int expectedCount = modCount;

        /**
         * Есть следующий элемент или нет.
         *
         * @return да/нет.
         */
        @Override
        public boolean hasNext() {
            boolean result = false;
            for (int current = index; current < this.array.length; current++) {
                if (this.array[current] != null) {
                    result = true;
                    break;
                }
            }
            return result;
        }

        /**
         * Получить следующий элемент списка, если он есть, иначе исключение.
         * Если со времени создания итератора список был изменен, то исключение.
         *
         * @return следующий элемент.
         */
        @Override
        public T next() {
            T result = null;
            if (this.hasNext()) {
                if (expectedCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                for (int pointer = index; pointer < this.array.length; pointer++) {
                    if (this.array[pointer] != null) {
                        result = (T) this.array[pointer];
                        this.index++;
                        break;
                    }
                }
            } else {
                throw new NoSuchElementException();
            }
            return result;
        }
    }
}