package ru.job4j.sort;

/**
 * Класс, описывающий юзера, является вспомогательным для SortUser.
 *
 * @author - b.savelev (mailto: justmustdie@yandex.ru)
 * @version - 1.0
 * @since 0.1
 */
public class User implements Comparable<User> {
    /**
     * Имя и возраст.
     */
    private String name;
    private int age;

    /**
     * Геттер.
     * @return возраст юзера.

     */
    public int getAge() {
        return age;
    }

    /**
     * Геттер.
     * @return имя юзера.

     */
    public String getName() {
        return name;
    }

    /**

     * Конструктор
     *
     * @param name имя.
     * @param age  возраст.
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Реализуем сравнение текущего объекта с переданным.
     *
     * @param o сравниваемый объект.
     * @return результат сравнения.
     */
    @Override
    public int compareTo(User o) {
        int result = Integer.compare(this.age, o.age);
        return result != 0 ? result : this.name.compareTo(o.name);
    }
}