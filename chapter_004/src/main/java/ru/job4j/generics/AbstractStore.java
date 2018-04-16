package ru.job4j.generics;

/**
 * Общая реализация интерфейса хранилища объектов, унаследованных от Base.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
abstract class AbstractStore<T extends Base> implements Store<T> {

    /**
     * Переменная с массивом, в котором хранятся объекты.
     */
    private SimpleArray<T> store = new SimpleArray<>();

    /**
     * Добавление объекта в хранилище
     *
     * @param model объект
     */
    public void add(T model) {
        this.store.add(model);
    }

    /**
     * Замена объекта в хранилище другим объектом по id.
     *
     * @param id    идентификатор объекта, который нужно заменить.
     * @param model объект для замены.
     * @return результат замены.
     */
    public boolean replace(String id, T model) {
        boolean result = false;
        for (int index = 0; index < this.store.length(); index++) {
            if (this.store.get(index) != null && this.store.get(index).getId().equals(id)) {
                this.store.set(index, model);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Удаление объекта из хранилища по id.
     *
     * @param id идентификатор объекта для удаления.
     * @return результат удаления.
     */
    public boolean delete(String id) {
        boolean result = false;
        for (int index = 0; index < this.store.length(); index++) {
            if (this.store.get(index) != null && this.store.get(index).getId().equals(id)) {
                this.store.delete(index);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Поиск объекта в хранилище по его id.
     *
     * @param id идентификатор для поиска.
     * @return найденный объект, если не найден - то Null.
     */
    public T findBy(String id) {
        T result = null;
        for (T some : this.store) {
            if (some != null && some.getId().equals(id)) {
                result = some;
                break;
            }
        }
        return result;
    }
}