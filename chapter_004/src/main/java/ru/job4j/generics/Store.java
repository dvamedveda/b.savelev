package ru.job4j.generics;

/**
 * Интерфейс, описывающий хранилище объектов, унаследованных от Base.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public interface Store<T extends Base> {

    /**
     * Добавление объекта в хранилище
     *
     * @param model объект
     */
    void add(T model);

    /**
     * Замена объекта в хранилище другим объектом по id.
     *
     * @param id    идентификатор объекта, который нужно заменить.
     * @param model объект для замены.
     * @return результат замены.
     */
    boolean replace(String id, T model);

    /**
     * Удаление объекта из хранилища по id.
     *
     * @param id идентификатор объекта для удаления.
     * @return результат удаления.
     */
    boolean delete(String id);

    /**
     * Поиск объекта в хранилище по его id.
     *
     * @param id идентификатор для поиска.
     * @return найденный объект, если не найден - то Null.
     */
    T findBy(String id);
}