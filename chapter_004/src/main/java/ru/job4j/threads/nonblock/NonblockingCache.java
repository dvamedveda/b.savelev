package ru.job4j.threads.nonblock;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Класс, реализующий неблокирующий кэш.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class NonblockingCache {

    /**
     * Хранилище объектов кэша.
     */
    private final ConcurrentHashMap<Integer, Base> store;

    /**
     * Конструктор кэша
     */
    public NonblockingCache() {
        this.store = new ConcurrentHashMap<>(16);
    }

    /**
     * Добавление объектов в кэш, добавляется, только если отсутствует.
     *
     * @param model новый объект.
     */
    public void add(Base model) {
        int id = model.getId();
        this.store.putIfAbsent(id, model);
    }

    /**
     * Обновление объекта в кэше.
     * Для обновления должен присутствовать в хранилище.
     * Если при обновлении версия изменилась (объект был модифицирован из другого потока),
     * то бросаем исключение типа OptimisticException.
     *
     * @param model обновляемая модель.
     * @param name  новое имя.
     */
    public void update(Base model, String name) {
        int id = model.getId();
        int version = model.getVersion();
        this.store.computeIfPresent(id, (k, v) -> {
            if (version == model.getVersion()) {
                v.update(name);
            } else {
                throw new OptimisticException("Version changed! Don't update");
            }
            return v;
        });
    }

    /**
     * Удаление объекта из хранилища.
     *
     * @param model удаляемый объект.
     */
    public void delete(Base model) {
        int id = model.getId();
        this.store.remove(id, model);
    }

    /**
     * Получение объекта по идентификатору.
     *
     * @param key идентификатор.
     * @return найденный объект или дефолтный, если объект ненайден.
     */
    public Base get(int key) {
        return this.store.getOrDefault(key, new Base(-1, "default"));
    }
}