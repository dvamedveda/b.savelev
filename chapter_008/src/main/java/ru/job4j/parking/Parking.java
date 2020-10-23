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
        boolean result;
        if (car.getSize() == 1) {
            result = this.light.canNextLight();
        } else {
            result = this.heavy.canNextHeavy(car.getSize()) || this.light.canNextHeavy(car.getSize());
        }
        return result;
    }

    /**
     * Припарковать автомобиль на стоянку.
     *
     * @param car паркуемый автомобиль.
     * @return была ли припаркована автомобиль.
     */
    public boolean park(Car car) {
        boolean result = false;
        if (checkPlace(car)) {
            if (car.getSize() == 1) {
                result = this.light.place(car);
            } else {
                result = this.heavy.place(car) || this.light.place(car);
            }
        }
        return result;
    }
}