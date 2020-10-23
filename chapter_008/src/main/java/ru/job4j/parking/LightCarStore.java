package ru.job4j.parking;

import java.util.Arrays;

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
     * @param size размер грузовой машины
     * @return да или нет.
     */
    @Override
    public boolean canNextHeavy(int size) {
        boolean result = size <= this.store.length;
        boolean havePlace = false;
        if (result) {
            for (int i = 0; i < this.store.length; i++) {
                if (havePlace) {
                    break;
                }
                if (i + size > this.store.length) {
                    result = false;
                    break;
                } else {
                    if (this.store[i] == null) {
                        for (int s = 1; s < size; s++) {
                            if (this.store[i + s] != null) {
                                havePlace = false;
                                break;
                            } else {
                                havePlace = true;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * Может ли стоянка вместить еще одну легковую машину.
     *
     * @return да или нет.
     */
    @Override
    public boolean canNextLight() {
        boolean result = false;
        for (Car car : this.store) {
            if (car == null) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Сколько мест еще доступно на стоянке.
     *
     * @return количество мест.
     */
    @Override
    public int placesAvailable() {
        int count = 0;
        for (Car car : this.store) {
            if (car == null) {
                count++;
            }
        }
        return count;
    }

    /**
     * Сколько всего мест содержит стоянка.
     *
     * @return количество мест.
     */
    @Override
    public int placesTotal() {
        return this.store.length;
    }

    /**
     * Разместить автомобиль на стоянка.
     *
     * @param car размещаемый автомобиль.
     * @return автомобиль размещен или нет.
     */
    @Override
    public boolean place(Car car) {
        boolean result = false;
        if (car.getSize() > 1) {
            for (int i = 0; i < this.store.length; i++) {
                if (result) {
                    break;
                }
                if (i + car.getSize() > this.store.length) {
                    result = false;
                    break;
                }
                if (suitableForHeavy(Arrays.copyOfRange(this.store, i, car.getSize() - 1))) {
                    for (int s = 0; s < car.getSize(); s++) {
                        this.store[i + s] = car;
                        result = true;
                    }
                }
            }
        } else {
            for (int i = 0; i < this.store.length; i++) {
                if (this.store[i] == null) {
                    car.setParkedOn(this.parkingName);
                    this.store[i] = car;
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /** Проверить, подходит ли подмассив для хранения грузового автомобиля.
     *
     * @param cars подмассив.
     * @return пусты ли все ячейки.
     */
    private boolean suitableForHeavy(Car[] cars) {
        boolean result = true;
        for (Car car : cars) {
            if (car != null) {
                result = false;
                break;
            }
        }
        return result;
    }
}