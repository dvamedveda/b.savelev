package ru.job4j.parking;

/**
 * Интерфейс, описывающий стоянку.
 */
public interface CarStore {

    /**
     * Может ли стоянка вместить еще одну грузовую машину.
     *
     * @return да или нет.
     */
    boolean canNextHeavy();

    /**
     * Может ли стоянка вместить еще одну легковую машину.
     *
     * @return да или нет.
     */
    boolean canNextLight();

    /**
     * Сколько мест еще доступно на стоянке.
     *
     * @return количество мест.
     */
    int placesAvailable();

    /**
     * Сколько всего мест содержит стоянка.
     *
     * @return количество мест.
     */
    int placesTotal();

    /**
     * Разместить автомобиль на стоянка.
     *
     * @param car размещаемый автомобиль.
     * @return автомобиль размещен или нет.
     */
    boolean place(Car car);
}