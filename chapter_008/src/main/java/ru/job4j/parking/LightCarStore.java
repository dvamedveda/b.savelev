package ru.job4j.parking;

/**
 * Реализация стоянки для легковых машин.
 */
public class LightCarStore implements CarStore {
    /**
     * Массив для хранения автомобилей стоянки.
     */
    private final Car[] store;
    /**
     * Название стоянки.
     */
    private final String parkingName = "Light car parking";

    /**
     * Устанавливаем размер стоянки.
     *
     * @param places количество мест.
     */
    public LightCarStore(int places) {
        this.store = new Car[places];
    }

    /**
     * Может ли стоянка вместить еще одну грузовую машину.
     *
     * @return да или нет.
     */
    @Override
    public boolean canNextHeavy() {
        return false;
    }

    /**
     * Может ли стоянка вместить еще одну легковую машину.
     *
     * @return да или нет.
     */
    @Override
    public boolean canNextLight() {
        return false;
    }

    /**
     * Сколько мест еще доступно на стоянке.
     *
     * @return количество мест.
     */
    @Override
    public int placesAvailable() {
        return 0;
    }

    /**
     * Сколько всего мест содержит стоянка.
     *
     * @return количество мест.
     */
    @Override
    public int placesTotal() {
        return 0;
    }

    /**
     * Разместить автомобиль на стоянка.
     *
     * @param car размещаемый автомобиль.
     * @return автомобиль размещен или нет.
     */
    @Override
    public boolean place(Car car) {
        return false;
    }
}