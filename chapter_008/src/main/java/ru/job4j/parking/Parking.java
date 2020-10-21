package ru.job4j.parking;

/**
 * Класс, реализующий сервис парковки машин.
 */
public class Parking {

    /**
     * Стоянка для легковых машин.
     */
    private final CarStore light;

    /**
     * Стоянка для грузовых машин.
     */
    private final CarStore heavy;

    /**
     * Задаем размеры стоянок для машин.
     *
     * @param lightPlaces количество мест для легковых машин.
     * @param heavyPlaces количество мест для грузовых машин.
     */
    public Parking(int lightPlaces, int heavyPlaces) {
        this.light = new LightCarStore(lightPlaces);
        this.heavy = new HeavyCarStore(heavyPlaces);
    }

    /**
     * Проверить, есть ли для автомобиля место.
     *
     * @param car автомобиль.
     * @return есть ли место.
     */
    public boolean checkPlace(Car car) {
        return false;
    }

    /**
     * Припарковать автомобиль на стоянку.
     *
     * @param car паркуемый автомобиль.
     * @return была ли припаркована автомобиль.
     */
    public boolean park(Car car) {
        return false;
    }
}